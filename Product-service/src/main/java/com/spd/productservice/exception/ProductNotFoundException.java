package com.spd.productservice.exception;

public class ProductNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String message;

    public ProductNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
