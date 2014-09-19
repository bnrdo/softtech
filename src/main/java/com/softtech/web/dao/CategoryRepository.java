package com.softtech.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softtech.web.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
