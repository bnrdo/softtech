package com.softtech.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softtech.web.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Role findByRoleName(String roleName);
	
	Role findByDescription(String description);
	
	Role findById(int id);
	
}