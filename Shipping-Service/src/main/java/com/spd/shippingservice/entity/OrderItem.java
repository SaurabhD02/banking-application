package com.spd.shippingservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class OrderItem {

    @MongoId
    private Integer productId;
    @MongoId
    private Integer orderId;
    private Integer orderedQuantity;
}
