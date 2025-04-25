package com.thingg.ecommerce.service;

import com.thingg.ecommerce.io.StripeOrderRequest;
import com.thingg.ecommerce.io.StripeOrderResponse;

public interface PaymentService {

    StripeOrderResponse create(StripeOrderRequest request);
}
