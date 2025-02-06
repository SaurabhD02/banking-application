package com.spd.userservice.exception;

public class AddressNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private String message;
    public AddressNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
