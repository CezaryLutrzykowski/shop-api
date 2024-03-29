package com.shop.order.service;

import com.shop.order.model.Payment;
import com.shop.order.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }
}
