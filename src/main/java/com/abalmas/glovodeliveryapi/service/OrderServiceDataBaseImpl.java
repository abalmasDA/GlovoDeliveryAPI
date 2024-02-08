package com.abalmas.glovodeliveryapi.service;


import com.abalmas.glovodeliveryapi.dto.OrderDto;
import com.abalmas.glovodeliveryapi.dto.ProductDto;
import com.abalmas.glovodeliveryapi.entity.OrderEntity;
import com.abalmas.glovodeliveryapi.entity.ProductEntity;
import com.abalmas.glovodeliveryapi.mapper.OrderMapper;
import com.abalmas.glovodeliveryapi.mapper.ProductMapper;
import com.abalmas.glovodeliveryapi.repository.OrderRepository;
import com.abalmas.glovodeliveryapi.repository.ProductRepository;
import com.abalmas.glovodeliveryapi.utils.exception.OrderNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * The type Order service database.
 */
@Service
@AllArgsConstructor
public class OrderServiceDataBaseImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;
  private final OrderMapper orderMapper;
  private final ProductMapper productMapper;


  @Override
  public List<OrderDto> findAll() {
    return orderRepository.findAll()
        .stream()
        .map(orderMapper::toDto)
        .collect(Collectors.toList());
  }

  /**
   * Finds an order by its ID.
   *
   * @param  id the ID of the order to find
   * @return    the order DTO if found, otherwise throws an OrderNotFoundException
   */
  public OrderDto findById(int id) {
    return orderRepository.findById(id)
        .map(orderMapper::toDto)
        .orElseThrow(() -> new OrderNotFoundException("Order with ID " + id + " not found"));
  }


  @Override
  public OrderDto add(OrderDto orderDto) {
    OrderEntity orderEntity = orderMapper.toEntity(orderDto);
    orderEntity.getProducts()
        .forEach(product -> product.setOrder(orderEntity));
    return orderMapper.toDto(orderRepository.save(orderEntity));
  }

  /**
   * Updates an order by ID with the provided order data.
   *
   * @param  id         the ID of the order to update
   * @param  orderDto   the order data to update
   * @return            the updated order data, or null if the order does not exist
   */
  @Override
  public OrderDto update(int id, OrderDto orderDto) {
    return orderRepository.findById(id).map(orderEntity -> {
      orderEntity.setCustomerName(orderDto.getCustomerName());
      OrderEntity updatedOrderEntity = orderRepository.save(orderEntity);
      return orderMapper.toDto(updatedOrderEntity);
    }).orElse(null);
  }

  /**
   * Adds a product to the order with the specified ID.
   *
   * @param  id           the ID of the order
   * @param  productDto   the product to be added
   * @return              an optional containing the updated order DTO if it exists, otherwise empty
   */
  public Optional<OrderDto> addProduct(int id, ProductDto productDto) {
    return orderRepository.findById(id).map(orderEntity -> {
      ProductEntity productEntity = productMapper.toEntity(productDto);
      productEntity.setOrder(orderEntity);
      List<ProductEntity> products = orderEntity.getProducts();
      products.add(productEntity);
      OrderEntity updatedOrderEntity = orderRepository.save(orderEntity);
      return orderMapper.toDto(updatedOrderEntity);
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
