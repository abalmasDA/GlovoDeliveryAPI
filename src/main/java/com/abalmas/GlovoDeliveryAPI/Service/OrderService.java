package com.abalmas.GlovoDeliveryAPI.Service;


import com.abalmas.GlovoDeliveryAPI.DTO.OrderDTO;
import com.abalmas.GlovoDeliveryAPI.DTO.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<OrderDTO> findAll();

    OrderDTO findById(int id);

    OrderDTO add(OrderDTO orderDTO);

    OrderDTO update(int id, OrderDTO orderDTO);

    Optional<OrderDTO> addProduct(int id, ProductDTO productDTO);

    void deleteProduct(int id, int productId);

    void delete(int id);


}
