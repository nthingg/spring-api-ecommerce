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
public class DashboardResponse {

    private Double todaySales;
    private Long todayOrders;
    private List<OrderResponse> recentOrders;
}
