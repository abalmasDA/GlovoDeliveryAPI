package com.abalmas.glovodeliveryapi.controller;

import com.abalmas.glovodeliveryapi.dto.OrderDto;
import com.abalmas.glovodeliveryapi.dto.ProductDto;
import com.abalmas.glovodeliveryapi.service.OrderServiceDataBaseImpl;
import com.abalmas.glovodeliveryapi.utils.response.ResponseHandler;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing orders in version 2 of the API. This controller provides HTTP methods for
 * performing CRUD (Create, Read, Update, Delete) operations on orders. It includes functionalities
 * for adding and removing products in orders, updating order details, and handling order deletion.
 * Each method in this controller is mapped to a specific HTTP request type (GET, POST, PUT, PATCH,
 * DELETE) for various endpoints under '/v2/orders'. The controller uses
 * fdgdfggddfgdfgdfgdfgdfgdfgdfgddfsdfsdfsdfsfgdfgdfg {@link OrderServiceDataBaseImpl} for service
 * layer operations.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/v2/orders")
public class OrderControllerV2 {

  private final OrderServiceDataBaseImpl orderServiceDataBaseImpl;

  @GetMapping

  public ResponseEntity<Object> getAll() {
    return ResponseHandler.responseBuilder("Requested data has been successfully displayed",
        HttpStatus.OK, orderServiceDataBaseImpl.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getById(@PathVariable int id) {
    return ResponseHandler.responseBuilder("Requested data has been successfully displayed",
        HttpStatus.OK, orderServiceDataBaseImpl.findById(id));
  }


  @PostMapping
  public ResponseEntity<Object> addOrder(@RequestBody @Valid OrderDto orderDto) {
    return ResponseEntity.ok(orderServiceDataBaseImpl.add(orderDto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateOrder(@PathVariable int id,
      @RequestBody @Valid OrderDto orderDto) {
    return ResponseEntity.ok(orderServiceDataBaseImpl.update(id, orderDto));
  }

  @PatchMapping("/{id}/products")
  public ResponseEntity<Object> addProduct(@PathVariable int id,
      @RequestBody @Valid ProductDto productDto) {
    return ResponseEntity.ok(orderServiceDataBaseImpl.addProduct(id, productDto));
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
