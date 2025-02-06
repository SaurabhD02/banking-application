package com.spd.favouriteservice.exception;

public class FavouriteNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String message;

    public FavouriteNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
