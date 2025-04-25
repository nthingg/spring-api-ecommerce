package com.thingg.ecommerce.controllers;

import com.thingg.ecommerce.io.OrderResponse;
import com.thingg.ecommerce.io.PaymentVerificationRequest;
import com.thingg.ecommerce.io.StripeOrderRequest;
import com.thingg.ecommerce.io.StripeOrderResponse;
import com.thingg.ecommerce.service.OrderService;
import com.thingg.ecommerce.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final OrderService orderService;

    @PostMapping("/create-charge")
    @ResponseStatus(HttpStatus.CREATED)
    public StripeOrderResponse addStripeCharge(@RequestBody StripeOrderRequest request) {
        return paymentService.create(request);
    }

    @PostMapping("/verify")
    public OrderResponse verifyCharge(@RequestBody PaymentVerificationRequest request) {
        return orderService.verifyPayment(request);
    }
}
