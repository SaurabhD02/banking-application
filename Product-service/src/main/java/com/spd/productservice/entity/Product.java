package com.spd.productservice.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends AbstractMappedEntity{

    @MongoId
    private String id;
    private String productId;
    private String name;
    private double price;
    private int quantity;
    private String description;
    private String categoryId;
}
