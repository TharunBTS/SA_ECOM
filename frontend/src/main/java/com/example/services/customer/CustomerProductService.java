package com.example.services.customer;

import java.util.List;

import com.example.dto.ProductDetailDto;
import com.example.dto.ProductDto;

public interface CustomerProductService {
	
	List<ProductDto> getAllCustomerProducts();
	
	List<ProductDto> getAllCustomerProductsByName(String name);
	
	ProductDetailDto getProductDetailById(Long productId);
}
