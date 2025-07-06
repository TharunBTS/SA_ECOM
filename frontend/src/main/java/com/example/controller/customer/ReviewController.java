package com.example.controller.customer;

import java.io.IOException;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.OrderedProductsResponseDto;
import com.example.dto.ReviewDto;
import com.example.services.customer.review.ReviewService;


@RestController
@RequestMapping("/api/customer")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	
	@GetMapping("/ordered-products/{orderId}")
	public ResponseEntity<OrderedProductsResponseDto> getOrderedProductsDetailsByOrderId(@PathVariable Long orderId){
		return ResponseEntity.ok(reviewService.getOrderedProductsDetailsByOrderId(orderId));
	}
	
	@PostMapping("/review")
	public ResponseEntity<?> giveReview(@ModelAttribute ReviewDto reviewDto) throws IOException
	{
		ReviewDto dto = reviewService.giveReview(reviewDto);
		if(dto == null ) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

}
