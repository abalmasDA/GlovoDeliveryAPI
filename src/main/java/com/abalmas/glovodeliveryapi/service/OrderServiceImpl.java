//package com.abalmas.GlovoDeliveryAPI.Service;
//
//
//import com.abalmas.GlovoDeliveryAPI.DTO.OrderDTO;
//import com.abalmas.GlovoDeliveryAPI.DTO.ProductDTO;
//import com.abalmas.GlovoDeliveryAPI.Repository.OrderRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@AllArgsConstructor
//public class OrderServiceImpl implements OrderService {
//
//    private final List<OrderDTO> orders = new ArrayList<>();
//
//    @Override
//    public List<OrderDTO> findAll() {
//        return orders;
//    }
//
//    @Override
//    public Optional<OrderDTO> findById(long id) {
//        return orders.stream()
//                .filter(order -> order.getId() == id)
//                .findFirst();
//    }
//
//    @Override
//    public OrderDTO add(OrderDTO orderDTO) {
//        orders.add(orderDTO);
//        return orderDTO;
//    }
//
//    @Override
//    public OrderDTO update(int id, OrderDTO orderDTO) {
//        Optional<OrderDTO> possibleOrder = findById(id);
//        return possibleOrder.map(order -> {
//            order.setId(orderDTO.getId());
//            order.setCustomerName(orderDTO.getCustomerName());
//            order.setTotalPrice(orderDTO.getTotalPrice());
//            order.setAdditionDate(orderDTO.getAdditionDate());
//            return order;
//        }).orElse(null);
//    }
//
//    @Override
//    public Optional<OrderDTO> addProduct(int id, ProductDTO productDTO) {
//        return findById(id)
//                .map(order -> {
//                    order.getProducts().add(productDTO);
//                    return order;
//                });
//    }
//
//    @Override
//    public void deleteProduct(int id, int productId) {
//        Optional<OrderDTO> order = findById(id);
//        order.ifPresent(o -> o.getProducts().removeIf(product -> product.getId() == productId));
//    }
//
//    @Override
//    public void delete(int id) {
//        Optional<OrderDTO> order = findById(id);
//        order.ifPresent(orders::remove);
//    }
//}
