package com.spd.shippingservice.exception.handler;

import com.spd.shippingservice.exception.CustomError;
import com.spd.shippingservice.exception.OrderItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderItemNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFoundException(final OrderItemNotFoundException ex) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .header(CustomError.Header.ORDER_ITEM_NOT_FOUND.getName())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }
}
