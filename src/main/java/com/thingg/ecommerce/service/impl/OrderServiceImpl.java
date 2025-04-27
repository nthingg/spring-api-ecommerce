package com.thingg.ecommerce.service.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.thingg.ecommerce.common.enums.PaymentStatus;
import com.thingg.ecommerce.entities.OrderEntity;
import com.thingg.ecommerce.entities.OrderItemEntity;
import com.thingg.ecommerce.io.OrderRequest;
import com.thingg.ecommerce.io.OrderResponse;
import com.thingg.ecommerce.io.PaymentDetails;
import com.thingg.ecommerce.common.enums.PaymentMethod;
import com.thingg.ecommerce.io.PaymentVerificationRequest;
import com.thingg.ecommerce.repositories.OrderItemRepository;
import com.thingg.ecommerce.repositories.OrderRepository;
import com.thingg.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Value("${stripe.access.key}")
    private String stripeApiKey;

    @Override
    public OrderResponse create(OrderRequest request) {
        OrderEntity newOrder = convertToOrderEntity(request);
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setStatus(PaymentStatus.SUCCEEDED);
        newOrder.setPaymentDetails(paymentDetails);

        List<OrderItemEntity> orderItems = request.getCartItems().stream()
                .map(this::convertToOrderItemEntity).toList();
        newOrder.setItems(orderItems);
        newOrder = orderRepository.save(newOrder);
        return convertToResponse(newOrder);
    }

    private OrderItemEntity convertToOrderItemEntity(OrderRequest.OrderItemRequest request) {
        return OrderItemEntity.builder()
                .itemId(request.getItemId())
                .name(request.getName())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .build();
    }

    private OrderResponse.OrderItemResponse convertToItemResponse(OrderItemEntity orderItem) {
        return OrderResponse.OrderItemResponse.builder()
                .itemId(orderItem.getItemId())
                .name(orderItem.getName())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .build();
    }

    private OrderResponse convertToResponse(OrderEntity newOrder) {
        return OrderResponse.builder()
                .orderId(newOrder.getOrderId())
                .customerName(newOrder.getCustomerName())
                .phone(newOrder.getPhone())
                .subtotal(newOrder.getSubtotal())
                .tax(newOrder.getTax())
                .grandTotal(newOrder.getGrandTotal())
                .paymentMethod(newOrder.getPaymentMethod())
                .cartItems(newOrder.getItems().stream()
                        .map(this::convertToItemResponse).toList())
                .paymentDetails(newOrder.getPaymentDetails())
                .createdAt(newOrder.getCreatedAt())
                .build();
    }

    private OrderEntity convertToOrderEntity(OrderRequest request) {
        return OrderEntity.builder()
                .customerName(request.getCustomerName())
                .phone(request.getPhone())
                .subtotal(request.getSubtotal())
                .tax(request.getTax())
                .grandTotal(request.getGrandTotal())
                .paymentMethod(PaymentMethod.valueOf(request.getPaymentMethod()))
                .build();
    }

    @Override
    public void delete(String orderId) {
        OrderEntity existingOrder = orderRepository.findByOrderId(orderId).
                orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(existingOrder);
    }

    @Override
    public List<OrderResponse> readLatest() {
        return orderRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::convertToResponse).toList();
    }

    @Override
    public Double sumSalesByDate(LocalDate date) {
        return orderRepository.sumSalesByDate(date);
    }

    @Override
    public Long countByOrderDate(LocalDate date) {
        return orderRepository.countByOrderDate(date);
    }

    @Override
    public List<OrderResponse> findRecent() {
        return orderRepository.findRecent(PageRequest.of(0,5))
                .stream()
                .map(this::convertToResponse).toList();
    }
}
