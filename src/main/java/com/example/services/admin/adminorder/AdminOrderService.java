package com.example.services.admin.adminorder;

import java.util.List;

import com.example.dto.AnalyticsResponse;
import com.example.dto.OrderDto;

public interface AdminOrderService {
	
	List<OrderDto> getAllPlacedOrder();
	OrderDto changeOrderStatus(Long orderId, String status);
	AnalyticsResponse calculateAnalytics();
}
