package com.spd.paymentservice.exception;

public class PaymentNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String message;

    public PaymentNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
