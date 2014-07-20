package com.softtech.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
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
			//id is bound in role name
			Role roleToAdd = roleService.getRoleById(Integer.parseInt(role.getRoleName()));
			loadedRoles.add(roleToAdd);
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

	@Override
	public List<UserAccount> findAll() {
		List<UserAccount> accounts = userAccountRepository.findAll(); 
		return accounts;
	}
	@Transactional(readOnly = false)
	@Modifying
	@Override
	public void removeUserAccountsByEmail(String[] emails) {
		List<UserAccount> accountsToRemove = new ArrayList<UserAccount>();
		for(String email : emails){
			UserAccount userAccount = userAccountRepository.findByEmail(email);
			userAccount.getRoles().clear();
			accountsToRemove.add(userAccount);
		}
		if(CollectionUtils.isNotEmpty(accountsToRemove)){
			userAccountRepository.delete(accountsToRemove);
		}
	}

	@Transactional(readOnly = false)
	@Override
	public void updateUserAccount(UserAccount userAccount) {
		UserAccount origAccount = userAccountRepository.findByEmail(userAccount.getEmail());
		if(origAccount!=null){
			origAccount.getRoles().clear();
			List<Role> loadedRoles = new ArrayList<Role>();
			for(Role role : userAccount.getRoles()) {
				//id is bound in role name
				Role roleToAdd = roleService.getRoleById(Integer.parseInt(role.getRoleName()));
				loadedRoles.add(roleToAdd);
			}
			origAccount.setStatus(userAccount.getStatus());
			origAccount.setRoles(loadedRoles);
			
			userAccountRepository.save(origAccount);
		}		
	}	
}