package com.thingg.ecommerce.service.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.thingg.ecommerce.io.StripeChargeRequest;
import com.thingg.ecommerce.io.StripeChargeResponse;
import com.thingg.ecommerce.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    @Value("${stripe.access.key}")
    private String stripeApiKey;

    @Override
    public StripeChargeResponse create(StripeChargeRequest request) {
        try {
            // Initialize Stripe API with the secret key
            Stripe.apiKey = stripeApiKey;

            // Create charge parameters
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", (int) (request.getAmount() * 100));
            chargeParams.put("currency", request.getCurrency());
            chargeParams.put("source", request.getSource());
            chargeParams.put("description", request.getDescription());

            Charge charge = Charge.create(chargeParams);

            return convertToResponse(charge);
        } catch (StripeException e) {
            throw new RuntimeException("Failed to create Stripe order: " + e.getMessage(), e);
        }
    }

    private StripeChargeResponse convertToResponse(Charge charge) {
        return StripeChargeResponse.builder()
                .id(charge.getId())
                .entity("charge")
                .amount(charge.getAmount().intValue())
                .currency(charge.getCurrency())
                .status(charge.getStatus())
                .created_at(new Date(charge.getCreated() * 1000)) // Convert to milliseconds
                .receipt(charge.getReceiptEmail())
                .build();
    }
}