package com.example.services.admin.faq;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.FAQDto;
import com.example.entity.FAQ;
import com.example.entity.Product;
import com.example.repository.FAQRepository;
import com.example.repository.ProductRepository;

@Service
public class FAQServiceImpl implements FAQService{
	
	@Autowired
	private FAQRepository faqRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public FAQDto postFAQ(Long productId, FAQDto faqDto) {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if(optionalProduct.isPresent()) {
			FAQ faq = new FAQ();
			
			faq.setQuestion(faqDto.getQuestion());
			faq.setAnswer(faqDto.getAnswer());
			faq.setProduct(optionalProduct.get());
			
			return faqRepository.save(faq).getFAQDto();
		}
		return null;
	}

}
