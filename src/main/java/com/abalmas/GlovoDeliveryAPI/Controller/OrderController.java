package com.abalmas.GlovoDeliveryAPI.Controller;

import com.abalmas.GlovoDeliveryAPI.DTO.OrderDTO;
import com.abalmas.GlovoDeliveryAPI.DTO.ProductDTO;
import com.abalmas.GlovoDeliveryAPI.Service.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderServiceImpl orderServiceImpl;

    @GetMapping
    public List<OrderDTO> getAll() {
        return orderServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public Optional<OrderDTO> getById(@PathVariable int id) {
        return orderServiceImpl.findById(id);
    }

    @PostMapping
    public OrderDTO addOrder(@RequestBody OrderDTO orderDTO) {
        return orderServiceImpl.add(orderDTO);
    }

    @PutMapping("/{id}")
    public OrderDTO updateOrder(@PathVariable int id, @RequestBody OrderDTO orderDTO) {
        return orderServiceImpl.update(id, orderDTO);
    }

    @PatchMapping("/{id}/products")
    public Optional<OrderDTO> addProduct(@PathVariable int id, @RequestBody ProductDTO productDTO) {
        return orderServiceImpl.addProduct(id, productDTO);
    }

    @DeleteMapping("/{id}/products/{idProduct}")
    public void deleteProduct(@PathVariable int id, @PathVariable int idProduct) {
        orderServiceImpl.deleteProduct(id, idProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable int id) {
        orderServiceImpl.delete(id);
    }


}
