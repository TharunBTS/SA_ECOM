package com.example.services.admin.faq;

import com.example.dto.FAQDto;

public interface FAQService {
	
	FAQDto postFAQ(Long productId, FAQDto faqDto);

}
