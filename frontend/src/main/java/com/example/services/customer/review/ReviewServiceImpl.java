package com.example.services.customer.review;
import com.example.repository.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.OrderedProductsResponseDto;
import com.example.dto.ProductDto;
import com.example.dto.ReviewDto;
import com.example.entity.CartItems;
import com.example.entity.Order;
import com.example.entity.Product;
import com.example.entity.Review;
import com.example.entity.User;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	

	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private OrderRepository orderRepository;

   
	
	public OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId) {
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		OrderedProductsResponseDto orderedProductsResponseDto = new OrderedProductsResponseDto();
		if(optionalOrder.isPresent()) {
			orderedProductsResponseDto.setOrderAmount(optionalOrder.get().getAmount());
			
			List<ProductDto> productDtoList = new ArrayList<>();
			for(CartItems cartItems : optionalOrder.get().getCartItems()) {
				ProductDto productDto = new ProductDto();
				productDto.setId(cartItems.getProduct().getId());
				productDto.setName(cartItems.getProduct().getName());
				productDto.setPrice(cartItems.getPrice());
				productDto.setQuantity(cartItems.getQuantity());
				productDto.setByteImg(cartItems.getProduct().getImg());
				
				productDtoList.add(productDto);
			}
			orderedProductsResponseDto.setProductDtoList(productDtoList);
			
		}
		return orderedProductsResponseDto;
	}
	
	
	
	public ReviewDto giveReview(ReviewDto reviewDto) throws IOException {
		Optional<Product> optionalProduct = productRepository.findById(reviewDto.getProductId());
		Optional<User> optionalUser = userRepository.findById(reviewDto.getUserId());
		
		if(optionalProduct.isPresent() && optionalUser.isPresent())
		{
			Review review = new Review();
			
			review.setRating(reviewDto.getRating());
			review.setDescription(reviewDto.getDescription());
			review.setProduct(optionalProduct.get());
			review.setUser(optionalUser.get());
			review.setImg(reviewDto.getImg().getBytes());
			
			return reviewRepository.save(review).getDto();
		}
		return null;
	}
}
