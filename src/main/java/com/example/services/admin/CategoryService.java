package com.example.services.admin;

import java.util.List;

import com.example.dto.CategoryDto;
import com.example.entity.Category;

public interface CategoryService {
	
	Category createCategory(CategoryDto categortDto);
	
	List<Category> getAllCategories();
	
}
