package com.example.services.admin.adminproduct;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.webauthn.management.PublicKeyCredentialRequestOptionsRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;


import com.example.dto.ProductDto;
import com.example.entity.Category;
import com.example.entity.Product;
import com.example.repository.CategoryRepository;
import com.example.repository.ProductRepository;

@Service
public class AdminProductServiceImpl implements AdminProductService{
	
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
public ProductDto addProduct(@ModelAttribute ProductDto productDto) throws IOException {
		
		Product product = new Product();
		
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setImg(productDto.getImg().getBytes());
		
		Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow();
		
		product.setCategory(category);
		return productRepository.save(product).getDto();
		
	}

	public List<ProductDto> getAllProducts(){
		List<Product> products = productRepository.findAll();
		return products.stream().map(Product::getDto).collect(Collectors.toList());
	}
	
	
	public List<ProductDto> getAllProductsByName(String name){
		List<Product> products = productRepository.findAllByNameContaining(name);
		return products.stream().map(Product::getDto).collect(Collectors.toList());
	}
	
	
	
    public boolean deleteProduct(Long id) {
    	Optional<Product> optionalProduct = productRepository.findById(id);
    	if(optionalProduct.isPresent()) {
    		productRepository.deleteById(id);
    		return true;
    	}
    	return false;
    }
    
    public ProductDto getProductById(Long productId) {
    	Optional<Product> optionalProduct = productRepository.findById(productId);
    	if(optionalProduct.isPresent())
    	{
    		return optionalProduct.get().getDto();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    
    public ProductDto updateProduct(Long productId, ProductDto productDto) {
    	Optional<Product> optionalProduct = productRepository.findById(productId);
    	Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
    	
    	if(optionalProduct.isPresent() && optionalCategory.isPresent())
    	{
    		Product product = optionalProduct.get();;
    		
    		product.setName(productDto.getName());
    		product.setCategory(optionalCategory.get());
    		product.setDescription(productDto.getDescription());
    		product.setPrice(productDto.getPrice());
    		
    		if(productDto.getByteImg() != null) {
    			product.setImg(productDto.getByteImg());
    		}
    		return productRepository.save(product).getDto();
    	}else {
    		return null;
    	}
    }
    
	
}
