package com.softtech.web.service;

import com.softtech.web.model.UserAccount;

public interface UserAccountService {
	
	void addUserAccount(UserAccount userAccount);
	
	UserAccount findUserAccountByName(String username);
	
	UserAccount findUserAccountByEmail(String email);
	
}