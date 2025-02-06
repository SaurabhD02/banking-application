package com.spd.productservice.service;

import com.spd.productservice.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    ProductDto addProduct(ProductDto productDto);

    ProductDto updateProduct(String productId, ProductDto productDto);

    ProductDto getProduct(String productId);

    List<ProductDto> getAllProducts();

    void deleteProduct(String productId);

    String uploadMaster(MultipartFile file);
}
