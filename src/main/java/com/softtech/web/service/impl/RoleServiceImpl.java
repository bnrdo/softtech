package com.softtech.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softtech.web.dao.RoleRepository;
import com.softtech.web.model.Role;
import com.softtech.web.service.RoleService;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
	
	@Inject
	private RoleRepository roleRepository;
	
	private final List<Role> rolesToRemoveFromList;
	
	{
		rolesToRemoveFromList = new ArrayList<Role>();
		rolesToRemoveFromList.add(new Role("ROLE_JOB_SEEKER"));
		rolesToRemoveFromList.add(new Role("ROLE_RECRUITER"));
	}
	
	public RoleServiceImpl() { }
	
	@Override
	public List<Role> getRolesForUserCreation() {
		
		List<Role> roles = roleRepository.findAll();
		roles.removeAll(rolesToRemoveFromList);
		
		return roles;
	}

	@Override
	public Role getRoleByRoleName(String roleName) {
		
		return roleRepository.findByRoleName(roleName);
		
	}

}