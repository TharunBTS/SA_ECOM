package com.example.services.admin.adminproduct;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.example.dto.ProductDto;

public interface AdminProductService {
	
	ProductDto addProduct(@RequestBody ProductDto productDto) throws IOException;
	
	List<ProductDto> getAllProducts();
	
	List<ProductDto> getAllProductsByName(String name);
	
	boolean deleteProduct(Long id);
	
	 ProductDto getProductById(Long productId);
	 
	 ProductDto updateProduct(Long productId, ProductDto productDto);
}
