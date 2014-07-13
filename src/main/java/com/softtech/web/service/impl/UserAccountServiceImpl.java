package com.softtech.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softtech.web.dao.UserAccountRepository;
import com.softtech.web.model.Role;
import com.softtech.web.model.UserAccount;
import com.softtech.web.service.RoleService;
import com.softtech.web.service.UserAccountService;

@Service
@Transactional(readOnly = true)
public class UserAccountServiceImpl implements UserAccountService {
	
	@Inject
	private UserAccountRepository userAccountRepository;
	
	@Inject
	private RoleService roleService;
	
	public UserAccountServiceImpl() { }
	
	@Transactional(readOnly = false)
	@Override
	public void addUserAccount(UserAccount userAccount) {
		
		List<Role> loadedRoles = new ArrayList<Role>();
		for(Role role : userAccount.getRoles()) {
			loadedRoles.add(roleService.getRoleByRoleName(role.getRoleName()));
		}
		
		userAccount.setRoles(loadedRoles);
		
		userAccountRepository.save(userAccount);
		
	}
	
	@Override
	public UserAccount findUserAccountByName(String username) {
		
		return userAccountRepository.findByUsername(username);
		
	}
	
	public UserAccount findUserAccountByEmail(String email) {
		
		return userAccountRepository.findByEmail(email);
		
	}

}