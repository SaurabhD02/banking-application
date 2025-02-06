package com.spd.paymentservice.dto;

import com.spd.basedomains.dto.OrderDto;
import com.spd.paymentservice.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    private Integer paymentId;
    private Boolean isPayed;
    private PaymentStatus paymentStatus;
    private OrderDto orderDto;
}
