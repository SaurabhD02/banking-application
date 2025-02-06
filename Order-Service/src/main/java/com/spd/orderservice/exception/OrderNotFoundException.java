package com.spd.orderservice.exception;

public class OrderNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String message;

    public OrderNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
