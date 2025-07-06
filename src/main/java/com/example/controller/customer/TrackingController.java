package com.example.controller.customer;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.OrderDto;
import com.example.services.customer.cart.CartService;

@RestController
public class TrackingController {
	
	
	@Autowired
	private CartService cartService;
	
	
	@GetMapping("/order/{trackingId}")
	public ResponseEntity<OrderDto> searchOrderByTrackingId(@PathVariable UUID trackingId){
		OrderDto orderDto = cartService.searchOrderByTrackingId(trackingId);
		if(orderDto == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(orderDto);
	}

}
