package com.spd.shippingservice.dto;

import com.spd.basedomains.dto.OrderDto;
import com.spd.basedomains.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private Integer productId;
    private Integer orderId;
    private Integer orderedQuantity;
    private ProductDto productDto;
    private OrderDto orderDto;
}
