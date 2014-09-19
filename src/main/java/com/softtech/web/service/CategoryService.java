package com.softtech.web.service;

import java.util.List;

import com.softtech.web.model.Category;

public interface CategoryService {
	void addCategory(Category category);
	void updateCategory(Category category);
	void deleteCategory(Category category);
	
	List<Category> findAll();	
}
