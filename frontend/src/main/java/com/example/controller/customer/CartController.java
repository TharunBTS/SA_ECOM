package com.example.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.AddProductInCartDto;
import com.example.dto.OrderDto;
import com.example.dto.PlaceOrderDto;
import com.example.services.customer.cart.CartService;

@RestController
@RequestMapping("/api/customer")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/cart")
	public ResponseEntity<?> addProductToCart(@RequestBody AddProductInCartDto addProductInCartDto)
	{
		return cartService.addProductToCart(addProductInCartDto);
	}
	
	@GetMapping("/cart/{userId}")
	public ResponseEntity<?> getCartByUserId(@PathVariable Long userId)
	{
		OrderDto orderDto = cartService.getCartByUserId(userId);
		return ResponseEntity.status(HttpStatus.OK).body(orderDto);
	}
	
	
	@PostMapping("/addition")
	public ResponseEntity<OrderDto> increasedProductQuantity(@RequestBody AddProductInCartDto addProductInCartDto)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.increaseProductQuantity(addProductInCartDto));
	}
	
	@PostMapping("/deduction")
	public ResponseEntity<OrderDto> decreasedProductQuantity(@RequestBody AddProductInCartDto addProductInCartDto)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.decreaseProductQuantity(addProductInCartDto));
	}
	
	
	@PostMapping("/placeOrder")
	public ResponseEntity<OrderDto> placeOrder(@RequestBody PlaceOrderDto placeOrderDto)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.placeOrder(placeOrderDto));
	}
	
	
	@GetMapping("/myOrders/{userId}")
	public ResponseEntity<List<OrderDto>> getMyPlacedOrders(@PathVariable Long userId)
	{
		return ResponseEntity.ok(cartService.getMyPlacedOrders(userId));
	}

}
