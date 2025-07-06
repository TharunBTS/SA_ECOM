package com.example.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.AnalyticsResponse;
import com.example.dto.OrderDto;
import com.example.services.admin.adminorder.AdminOrderService;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {
	
	@Autowired
	private AdminOrderService adminOrderService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/placedOrders")
	public ResponseEntity<List<OrderDto>> getAllPLacedOrders(){
		return ResponseEntity.ok(adminOrderService.getAllPlacedOrder());
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/order/{orderId}/{status}")
	public ResponseEntity<?> changeOrderStatus(@PathVariable Long orderId, @PathVariable String status){
		
		OrderDto orderDto = adminOrderService.changeOrderStatus(orderId, status);
		if(orderDto == null) {
			return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
			
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(orderDto);
	}
	
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/order/analytics")
	public ResponseEntity<AnalyticsResponse> getAnalytics()
	{
		return ResponseEntity.ok(adminOrderService.calculateAnalytics());
		}

}
