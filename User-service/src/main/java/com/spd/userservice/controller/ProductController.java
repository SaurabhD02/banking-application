package com.spd.userservice.controller;

import com.spd.userservice.entity.Product;
import com.spd.userservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @PostMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        List<Product> products = productService.getAllProducts(pageNo, pageSize);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/sort")
    public ResponseEntity<Page<Product>> getAllProductsSortedByCategory(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        Page<Product> products = productService.getAllProductsSorted(pageNo, pageSize);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


}
