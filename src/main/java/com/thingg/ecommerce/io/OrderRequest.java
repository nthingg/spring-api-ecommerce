package com.thingg.ecommerce.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private String customerName;
    private String phone;
    private List<OrderItemRequest> cartItems;
    private Double subtotal;
    private Double tax;
    private Double grandTotal;
    private String paymentMethod;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItemRequest {
        private String itemId;
        private String name;
        private Double price;
        private Integer quantity;
    }
}
