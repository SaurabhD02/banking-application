package com.spd.productservice.entity;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Set;

@Document(collection = "categories")
public class Category {

    @MongoId
    private Integer categoryId;
    private String categoryTitle;
    @DBRef
    private Set<Product> products;
}
