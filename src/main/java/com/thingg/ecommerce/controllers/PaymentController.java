package com.thingg.ecommerce.controllers;

import com.thingg.ecommerce.io.OrderResponse;
import com.thingg.ecommerce.io.PaymentVerificationRequest;
import com.thingg.ecommerce.io.StripeChargeRequest;
import com.thingg.ecommerce.io.StripeChargeResponse;
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

    @PostMapping("/create-charge")
    @ResponseStatus(HttpStatus.CREATED)
    public StripeChargeResponse addStripeCharge(@RequestBody StripeChargeRequest request) {
        return paymentService.create(request);
    }
}
