package com.example.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class ProductDetailDto {
	
	
	@Autowired
	private ProductDto productDto;
	
	
	private List<ReviewDto> reviewDtoList;
	
	private List<FAQDto> faqtoList;

	public ProductDto getProductDto() {
		return productDto;
	}

	public void setProductDto(ProductDto productDto) {
		this.productDto = productDto;
	}

	public List<ReviewDto> getReviewDtoList() {
		return reviewDtoList;
	}

	public void setReviewDtoList(List<ReviewDto> reviewDtoList) {
		this.reviewDtoList = reviewDtoList;
	}

	public List<FAQDto> getFaqtoList() {
		return faqtoList;
	}

	public void setFaqtoList(List<FAQDto> faqtoList) {
		this.faqtoList = faqtoList;
	}
	
	
	
	

}
