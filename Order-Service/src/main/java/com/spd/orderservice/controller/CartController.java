package com.spd.orderservice.controller;

import com.spd.orderservice.dto.CartDto;
import com.spd.orderservice.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@AllArgsConstructor
public class CartController {

    private CartService cartService;

    @PostMapping
    public ResponseEntity<CartDto> addCart(@RequestBody CartDto cartDto) {
        return new ResponseEntity<>(cartService.save(cartDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CartDto>> getAllCarts() {
        return new ResponseEntity<>(cartService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCartById(@PathVariable("cartId") int cartId) {
        return new ResponseEntity<>(cartService.findById(cartId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CartDto> updateCart(@RequestBody CartDto cartDto) {
        return new ResponseEntity<>(cartService.update(cartDto), HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<CartDto> deleteCart(@PathVariable("cartId") int cartId) {
        cartService.deleteById(cartId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
