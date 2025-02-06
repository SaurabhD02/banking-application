package com.spd.paymentservice.service;

import com.spd.paymentservice.dto.PaymentDto;

import java.util.List;

public interface PaymentService {

    public PaymentDto createPayment(PaymentDto paymentDto);
    public PaymentDto updatePayment(PaymentDto paymentDto);
    public PaymentDto getPayment(int paymentId);
    public void deletePayment(int paymentId);
    public List<PaymentDto> getAllPayments();
}
