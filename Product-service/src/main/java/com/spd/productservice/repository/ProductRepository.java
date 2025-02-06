package com.spd.productservice.repository;

import com.spd.productservice.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findByProductId(String id);
}
