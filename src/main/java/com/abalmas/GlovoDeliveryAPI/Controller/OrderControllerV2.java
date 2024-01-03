package com.abalmas.GlovoDeliveryAPI.Controller;

import com.abalmas.GlovoDeliveryAPI.DTO.OrderDTO;
import com.abalmas.GlovoDeliveryAPI.DTO.ProductDTO;
import com.abalmas.GlovoDeliveryAPI.Utils.Response.ResponseHandler;
import com.abalmas.GlovoDeliveryAPI.Service.OrderServiceDataBaseImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/v2/orders")
public class OrderControllerV2 {

    private final OrderServiceDataBaseImpl orderServiceDataBaseImpl;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        return ResponseHandler.responseBuilder("Requested data has been successfully displayed", HttpStatus.OK, orderServiceDataBaseImpl.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        return ResponseHandler.responseBuilder("Requested data has been successfully displayed", HttpStatus.OK, orderServiceDataBaseImpl.findById(id));
    }


    @PostMapping
    public ResponseEntity<Object> addOrder(@RequestBody @Valid OrderDTO orderDTO) {
        return ResponseEntity.ok(orderServiceDataBaseImpl.add(orderDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrder(@PathVariable int id, @RequestBody @Valid OrderDTO orderDTO) {
        return ResponseEntity.ok(orderServiceDataBaseImpl.update(id, orderDTO));
    }

    @PatchMapping("/{id}/products")
    public ResponseEntity<Object> addProduct(@PathVariable int id, @RequestBody @Valid ProductDTO productDTO) {
        return ResponseEntity.ok(orderServiceDataBaseImpl.addProduct(id, productDTO));
    }

    @DeleteMapping("/{id}/products/{idProduct}")
    public void deleteProduct(@PathVariable int id, @PathVariable int idProduct) {
        orderServiceDataBaseImpl.deleteProduct(id, idProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable int id) {
        orderServiceDataBaseImpl.delete(id);
    }


}
