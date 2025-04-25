package com.thingg.ecommerce.io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StripeOrderRequest {
    private Integer amount;
    private String currency;
    private String source;
    private String receipt;
    private String description;
}
