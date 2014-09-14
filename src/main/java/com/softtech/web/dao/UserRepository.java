package com.softtech.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.web.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
		
}