package com.softtech.web.service;

import java.util.List;

import com.softtech.web.form.Registration;
import com.softtech.web.model.UserAccount;

public interface UserAccountService {
	
	void addUserAccount(UserAccount userAccount);
	
	void updateUserAccount(UserAccount userAccount);
	
	UserAccount findUserAccountByName(String username);
	
	UserAccount findUserAccountByEmail(String email);
	
	List<UserAccount> findAll();
	
	void removeUserAccountsByEmail(String[] emails);

	UserAccount findUserAccountByPasswordResetToken(String token);

	void updateUserAccountStatusAndRoles(UserAccount userAccount);
	
	UserAccount toUserAccount(Registration regForm);
	
}