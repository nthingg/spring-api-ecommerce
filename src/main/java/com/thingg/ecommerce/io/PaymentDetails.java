package com.thingg.ecommerce.io;

import com.thingg.ecommerce.common.enums.PaymentStatus;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDetails {
    private String stripeChargeId;
    private PaymentStatus status;
}
