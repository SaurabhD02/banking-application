package com.spd.shippingservice.service;

import com.spd.shippingservice.dto.OrderItemDto;
import com.spd.shippingservice.entity.OrderItemId;

import java.util.List;

public interface ShippingService {

    List<OrderItemDto> findAll();
    OrderItemDto findById(final OrderItemId orderItemId);
    OrderItemDto save(final OrderItemDto orderItemDto);
    OrderItemDto update(final OrderItemDto orderItemDto);
    void deleteById(final OrderItemId orderItemId);
}
