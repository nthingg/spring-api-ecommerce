package com.thingg.ecommerce.io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StripeChargeRequest {
    private Integer amount;
    private String currency;
    private String source;
    private String description;
}
