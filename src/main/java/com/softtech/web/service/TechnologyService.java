package com.softtech.web.service;

import java.util.List;

import com.softtech.web.model.Category;
import com.softtech.web.model.Technology;

public interface TechnologyService {
	void addTechnology(Technology technology);
	void updateTechnology(Technology technology);
	void deleteTechnology(Technology technology);
	
	List<Technology> findAll();
	List<Technology> findAllByCategory(Category category);	
}
