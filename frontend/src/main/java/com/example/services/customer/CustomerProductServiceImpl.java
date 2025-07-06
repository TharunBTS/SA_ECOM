package com.example.services.customer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.ProductDetailDto;
import com.example.dto.ProductDto;
import com.example.entity.FAQ;
import com.example.entity.Product;
import com.example.entity.Review;
import com.example.repository.FAQRepository;
import com.example.repository.ProductRepository;
import com.example.repository.ReviewRepository;

@Service
public class CustomerProductServiceImpl implements CustomerProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private FAQRepository faqRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	public List<ProductDto> getAllCustomerProducts(){
		List<Product> products = productRepository.findAll();
		return products.stream().map(Product::getDto).collect(Collectors.toList());
	}
	
	
	public List<ProductDto> getAllCustomerProductsByName(String name){
		List<Product> products = productRepository.findAllByNameContaining(name);
		return products.stream().map(Product::getDto).collect(Collectors.toList());
	}
	
	
	public ProductDetailDto getProductDetailById(Long productId) {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if(optionalProduct.isPresent()) {
			List<FAQ> faqList = faqRepository.findAllByProduct_Id(productId);
			System.out.println("FAQs fetched: " + faqList.size());
			List<Review> reviewList = reviewRepository.findAllByProduct_Id(productId);
			System.out.println(reviewList);
			ProductDetailDto productDetailsDto = new ProductDetailDto();
			productDetailsDto.setProductDto(optionalProduct.get().getDto());
			productDetailsDto.setFaqtoList(faqList.stream().map(FAQ::getFAQDto).collect(Collectors.toList()));
			productDetailsDto.setReviewDtoList(reviewList.stream().map(Review::getDto).collect(Collectors.toList()));
			return productDetailsDto;
		}
		return null;
	}
	
	
	
}
