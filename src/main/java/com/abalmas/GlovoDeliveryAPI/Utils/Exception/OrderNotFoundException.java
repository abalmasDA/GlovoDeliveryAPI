package com.abalmas.GlovoDeliveryAPI.Utils.Exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
