package com.spd.userservice.repository;

import com.spd.userservice.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductPagingRepository extends PagingAndSortingRepository<Product, Long> {
}
