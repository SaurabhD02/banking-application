package com.spd.shippingservice.repository;

import com.spd.shippingservice.entity.OrderItem;
import com.spd.shippingservice.entity.OrderItemId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShippingServiceRepository extends MongoRepository<OrderItem, OrderItemId> {
}
