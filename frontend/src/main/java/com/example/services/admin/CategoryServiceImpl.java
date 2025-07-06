package com.example.services.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.CategoryDto;
import com.example.entity.Category;
import com.example.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category createCategory(CategoryDto categortDto)
	{
		Category category = new Category();
		category.setName(categortDto.getName());
		category.setDescription(categortDto.getDescription());
		
		return categoryRepository.save(category);
	}
	
	public List<Category> getAllCategories(){
		return categoryRepository.findAll();
	}

}
