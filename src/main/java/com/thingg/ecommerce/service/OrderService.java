package com.thingg.ecommerce.service;

import com.thingg.ecommerce.io.OrderRequest;
import com.thingg.ecommerce.io.OrderResponse;
import com.thingg.ecommerce.io.PaymentVerificationRequest;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    OrderResponse create(OrderRequest request);

    void delete(String orderId);

    List<OrderResponse> readLatest();

    Double sumSalesByDate(LocalDate date);

    Long countByOrderDate(LocalDate date);

    List<OrderResponse> findRecent();
}
