package com.spd.userservice.service;

import com.spd.userservice.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts(int pageNo, int pageSize);

    Page<Product> getAllProductsSorted(int pageNo, int pageSize);
}
