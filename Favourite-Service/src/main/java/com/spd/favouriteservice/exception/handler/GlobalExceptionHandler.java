package com.spd.favouriteservice.exception.handler;

import com.spd.favouriteservice.exception.CustomError;
import com.spd.favouriteservice.exception.FavouriteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FavouriteNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFoundException(final FavouriteNotFoundException ex) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .header(CustomError.Header.FAVOURITE_NOT_FOUND.getName())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }
}
