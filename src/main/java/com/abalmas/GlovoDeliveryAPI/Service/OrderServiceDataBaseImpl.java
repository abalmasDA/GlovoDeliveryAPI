package com.abalmas.GlovoDeliveryAPI.Service;


import com.abalmas.GlovoDeliveryAPI.DTO.OrderDTO;
import com.abalmas.GlovoDeliveryAPI.DTO.ProductDTO;
import com.abalmas.GlovoDeliveryAPI.Entity.OrderEntity;
import com.abalmas.GlovoDeliveryAPI.Entity.ProductEntity;
import com.abalmas.GlovoDeliveryAPI.Mapper.OrderMapper;
import com.abalmas.GlovoDeliveryAPI.Mapper.ProductMapper;
import com.abalmas.GlovoDeliveryAPI.Repository.OrderRepository;
import com.abalmas.GlovoDeliveryAPI.Repository.ProductRepository;
import com.abalmas.GlovoDeliveryAPI.Utils.Exception.OrderNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceDataBaseImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;


    @Override
    public List<OrderDTO> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }


    public OrderDTO findById(int id) {
        return orderRepository.findById(id)
                .map(orderMapper::toDTO)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID " + id + " not found"));
    }


    @Override
    public OrderDTO add(OrderDTO orderDTO) {
        OrderEntity orderEntity = orderMapper.toEntity(orderDTO);
        orderEntity.getProducts()
                .forEach(product -> product.setOrder(orderEntity));
        return orderMapper.toDTO(orderRepository.save(orderEntity));
    }


    @Override
    public OrderDTO update(int id, OrderDTO orderDTO) {
        return orderRepository.findById(id).map(orderEntity -> {
            orderEntity.setCustomerName(orderDTO.getCustomerName());
            OrderEntity updatedOrderEntity = orderRepository.save(orderEntity);
            return orderMapper.toDTO(updatedOrderEntity);
        }).orElse(null);
    }

    public Optional<OrderDTO> addProduct(int id, ProductDTO productDTO) {
        return orderRepository.findById(id).map(orderEntity -> {
            ProductEntity productEntity = productMapper.toEntity(productDTO);
            productEntity.setOrder(orderEntity);
            List<ProductEntity> products = orderEntity.getProducts();
            products.add(productEntity);
            OrderEntity updatedOrderEntity = orderRepository.save(orderEntity);
            return orderMapper.toDTO(updatedOrderEntity);
        });
    }

    @Override
    public void deleteProduct(int id, int productId) {
        orderRepository.findById(id).ifPresent(orderEntity -> {
            List<ProductEntity> products = orderEntity.getProducts();
            products.removeIf(product -> product.getId() == productId);
            orderRepository.save(orderEntity);
            productRepository.deleteById(productId);
        });
    }

    @Override
    public void delete(int id) {
        orderRepository.deleteById(id);
    }
}
