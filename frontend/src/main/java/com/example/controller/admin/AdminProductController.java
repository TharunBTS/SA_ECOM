package com.example.controller.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.FAQDto;
import com.example.dto.ProductDto;
import com.example.repository.ProductRepository;
import com.example.services.admin.adminproduct.AdminProductService;
import com.example.services.admin.faq.FAQService;

@RestController
@RequestMapping("/api/admin")
public class AdminProductController {

    
	
    @Autowired
	private AdminProductService adminProductService;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private FAQService faqService;
	

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/product")
    public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto) throws IOException {
    	ProductDto productDto1 = adminProductService.addProduct(productDto);
    	return ResponseEntity.status(HttpStatus.CREATED).body(productDto1);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
    	List<ProductDto> productDtos = adminProductService.getAllProducts();
    	return ResponseEntity.ok(productDtos);
    }
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDto>> getAllProductsByName(@PathVariable String name){
    	List<ProductDto> productDtos = adminProductService.getAllProductsByName(name);
    	return ResponseEntity.ok(productDtos);
    }
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<List<ProductDto>> deleteProduct(@PathVariable Long productId){
    	boolean deleted = adminProductService.deleteProduct(productId);
    	if(deleted)
    	{
    		return ResponseEntity.noContent().build() ;
    	}
   
    	return ResponseEntity.notFound().build() ;
    }
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/faq/{productId}")
    public ResponseEntity<FAQDto> postFAQ(@PathVariable Long productId, @RequestBody FAQDto faqDto) throws IOException {
    	
    	return ResponseEntity.status(HttpStatus.CREATED).body(faqService.postFAQ(productId, faqDto));
    }
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId){
    	ProductDto productDto = adminProductService.getProductById(productId);
    	if(productDto != null)
    	{
    		return ResponseEntity.ok(productDto);
    	}
   
    	return ResponseEntity.notFound().build() ;
    }
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/product/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId, @ModelAttribute ProductDto productDto){
    	ProductDto updatedProduct = adminProductService.updateProduct(productId, productDto);
    	if(updatedProduct != null)
    	{
    		return ResponseEntity.ok(updatedProduct);
    	}
    	
    	return ResponseEntity.notFound().build() ;
    }

}
    
   
