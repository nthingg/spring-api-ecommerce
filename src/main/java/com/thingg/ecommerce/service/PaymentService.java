package com.thingg.ecommerce.service;

import com.thingg.ecommerce.io.StripeChargeRequest;
import com.thingg.ecommerce.io.StripeChargeResponse;

public interface PaymentService {

    StripeChargeResponse create(StripeChargeRequest request);
}
