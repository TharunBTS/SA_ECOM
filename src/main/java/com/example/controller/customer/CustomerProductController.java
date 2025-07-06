package com.example.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ProductDetailDto;
import com.example.dto.ProductDto;
import com.example.services.customer.CustomerProductService;

@RestController
@RequestMapping("/api/customer")
public class CustomerProductController {
	
	@Autowired
	private CustomerProductService customerProductService;
	
//	@PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllCustomerProducts(){
    	List<ProductDto> productDtos = customerProductService.getAllCustomerProducts();
    	return ResponseEntity.ok(productDtos);
    }
    
    
//    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDto>> getAllCustomerProductsByName(@PathVariable String name){
    	List<ProductDto> productDtos = customerProductService.getAllCustomerProductsByName(name);
    	return ResponseEntity.ok(productDtos);
    }
    
    
    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDetailDto> getProductDetailById(@PathVariable Long productId){
    	ProductDetailDto productDetailDto = customerProductService.getProductDetailById(productId);
    	if(productDetailDto == null) return ResponseEntity.notFound().build();
    	return ResponseEntity.ok(productDetailDto);
    }

}
