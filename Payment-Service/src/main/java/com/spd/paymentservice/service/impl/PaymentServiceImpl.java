package com.spd.paymentservice.service.impl;

import com.spd.paymentservice.dto.PaymentDto;
import com.spd.paymentservice.entity.Payment;
import com.spd.paymentservice.exception.PaymentNotFoundException;
import com.spd.paymentservice.repository.PaymentRepository;
import com.spd.paymentservice.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private ModelMapper modelMapper;
    private PaymentRepository paymentRepository;

    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {
        log.info("*** PaymentDto, serviceImpl; save payment *");
        Payment payment = modelMapper.map(paymentDto, Payment.class);
        Payment savedPayment = paymentRepository.save(payment);
        return modelMapper.map(savedPayment, PaymentDto.class);
    }

    @Override
    public PaymentDto updatePayment(PaymentDto paymentDto) {
        log.info("*** PaymentDto, serviceImpl; update payment *");
        Payment payment = modelMapper.map(paymentDto, Payment.class);
        Payment updatedPayment = paymentRepository.save(payment);
        return modelMapper.map(updatedPayment, PaymentDto.class);
    }

    @Override
    public PaymentDto getPayment(int paymentId) {
        log.info("*** PaymentDto, serviceImpl; get payment with paymentId *");
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(()->new PaymentNotFoundException("Payment not found with paymentId"+ paymentId));
        return modelMapper.map(payment, PaymentDto.class);
    }

    @Override
    public void deletePayment(int paymentId) {
        log.info("*** PaymentDto, serviceImpl; delete payment with paymentId *");
        paymentRepository.deleteById(paymentId);
    }

    @Override
    public List<PaymentDto> getAllPayments() {
        log.info("*** PaymentDto List, serviceImpl; fetch all payments *");
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream().map(payment -> modelMapper.map(payment, PaymentDto.class)).collect(Collectors.toList());
    }
}
