package com.example.services.customer.review;


import java.io.IOException;

import com.example.dto.OrderedProductsResponseDto;
import com.example.dto.ReviewDto;


public interface ReviewService {
	
	OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId);
	ReviewDto giveReview(ReviewDto reviewDto) throws IOException ;
}
