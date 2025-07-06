package com.example.services.customer.cart;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.example.dto.AddProductInCartDto;
import com.example.dto.OrderDto;
import com.example.dto.PlaceOrderDto;

public interface CartService {
	
	ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);
	
	
	OrderDto getCartByUserId(Long userId);
	
	OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto);
	
	OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto);
	
	 OrderDto placeOrder(PlaceOrderDto placeOrderDto);
	 
	 List<OrderDto> getMyPlacedOrders(Long userId);
	 
	 OrderDto searchOrderByTrackingId(UUID trackingId);
}
