package com.abalmas.glovodeliveryapi.service;


import com.abalmas.glovodeliveryapi.dto.OrderDto;
import com.abalmas.glovodeliveryapi.dto.ProductDto;
import java.util.List;
import java.util.Optional;

/**
 * The interface Order service.
 */
public interface OrderService {

  List<OrderDto> findAll();

  OrderDto findById(int id);

  OrderDto add(OrderDto orderDto);

  OrderDto update(int id, OrderDto orderDto);

  Optional<OrderDto> addProduct(int id, ProductDto productDto);

  void deleteProduct(int id, int productId);

  void delete(int id);


}
