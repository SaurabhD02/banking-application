package com.spd.orderservice.dto;

import com.spd.basedomains.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private int cartId;
    private int userName;
    private Set<OrderDto> orderDtos;
    private UserDto userDto;
}
