package com.spd.shippingservice.controller;

import com.spd.shippingservice.dto.OrderItemDto;
import com.spd.shippingservice.entity.OrderItemId;
import com.spd.shippingservice.service.ShippingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderItems")
@AllArgsConstructor
@Slf4j
public class OrderItemController {

    private ShippingService shippingService;

    @PostMapping
    public ResponseEntity<OrderItemDto> createOrderItem(@RequestBody OrderItemDto orderItemDto) {
        return new ResponseEntity<>(shippingService.save(orderItemDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderItemDto>> getAllOrderItems() {
        return new ResponseEntity<>(shippingService.findAll(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<OrderItemDto> getOrderItemById(@RequestParam OrderItemId orderItemId) {
        return new ResponseEntity<>(shippingService.findById(orderItemId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<OrderItemDto> updateOrderItem(@RequestBody OrderItemDto orderItemDto) {
        return new ResponseEntity<>(shippingService.update(orderItemDto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<OrderItemDto> deleteOrderItemById(@RequestParam OrderItemId orderItemId) {
        shippingService.deleteById(orderItemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
