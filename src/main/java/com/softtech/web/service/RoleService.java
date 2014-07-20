package com.softtech.web.service;

import java.util.List;

import com.softtech.web.model.Role;

public interface RoleService {
	
	List<Role> getRolesForUserCreation();
	
	Role getRoleByRoleName(String roleName);
	
	Role getRoleByDescription(String description);
	
	Role getRoleById(int id);
	
}