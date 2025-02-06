package com.spd.userservice.service.impl;

import com.spd.userservice.entity.Product;
import com.spd.userservice.repository.ProductPagingRepository;
import com.spd.userservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductPagingRepository productPagingRepository;

    @Override
    public List<Product> getAllProducts(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<Product> products = productPagingRepository.findAll(pageable).getContent();
        return products;
    }

    @Override
    public Page<Product> getAllProductsSorted(int pageNo, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, "categoryId");
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Product> products = productPagingRepository.findAll(pageable);
        return products;
    }
}
