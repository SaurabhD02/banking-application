package com.spd.productservice.exception.handler;

import com.spd.productservice.exception.CustomError;
import com.spd.productservice.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFoundException(final ProductNotFoundException ex) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .header(CustomError.Header.PRODUCT_NOT_FOUND.getName())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }
}
