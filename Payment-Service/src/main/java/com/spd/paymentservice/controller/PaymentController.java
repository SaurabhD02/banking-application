package com.spd.paymentservice.controller;

import com.spd.paymentservice.dto.PaymentDto;
import com.spd.paymentservice.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class PaymentController {

    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDto> addPayment(@RequestBody PaymentDto paymentDto) {
        return new ResponseEntity<>(paymentService.createPayment(paymentDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PaymentDto> updatePayment(@RequestBody PaymentDto paymentDto) {
        return new ResponseEntity<>(paymentService.updatePayment(paymentDto), HttpStatus.OK);
    }
    @DeleteMapping("/{paymentId}")
    public ResponseEntity<?> deletePayment(@PathVariable("paymentId") int paymentId) {
        paymentService.deletePayment(paymentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PaymentDto>> getAllPayments() {
        return new ResponseEntity<>(paymentService.getAllPayments(), HttpStatus.OK);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDto> getPayment(@PathVariable("paymentId") int paymentId) {
        return new ResponseEntity<>(paymentService.getPayment(paymentId), HttpStatus.OK);
    }
}
