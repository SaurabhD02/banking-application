package com.spd.shippingservice.exception;

public class OrderItemNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private String message;

    public OrderItemNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
