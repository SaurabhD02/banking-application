package com.spd.productservice.controller;

import com.spd.productservice.dto.ProductDto;
import com.spd.productservice.entity.Product;
import com.spd.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.addProduct(productDto), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("productId") String productId) {
        return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("productId") String productId, @RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.updateProduct(productId, productDto), HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") String productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>("Product deleted succesfully with product Id - > "+productId,HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadProductMaster(@RequestParam MultipartFile file) {
        return new ResponseEntity<>(productService.uploadMaster(file), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadProductMasterUsingSpringBatch(@RequestParam MultipartFile file) {
        return new ResponseEntity<>(productService.uploadMaster(file), HttpStatus.OK);
    }
}
