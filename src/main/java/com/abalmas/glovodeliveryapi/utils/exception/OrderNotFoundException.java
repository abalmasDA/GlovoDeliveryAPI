package com.abalmas.glovodeliveryapi.utils.exception;

/**
 * The type Order not found exception.
 */
public class OrderNotFoundException extends RuntimeException {

  public OrderNotFoundException(String message) {
    super(message);
  }
}
