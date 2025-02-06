package com.spd.basedomains.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private int orderId;
    private LocalDateTime orderDate;
    private String desc;
    private double orderAmount;
}
