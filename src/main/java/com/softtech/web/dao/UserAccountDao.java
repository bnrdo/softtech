package com.softtech.web.dao;

import java.util.List;

import com.softtech.web.model.UserAccount;

public interface UserAccountDao {
	void addUserAccount(UserAccount user);
	void editUserAccount(UserAccount user);
	void deleteUserAccount(int userId);
	UserAccount findUserAccount(int userId);
	UserAccount findUserAccountByName(String username);
	UserAccount findUserAccountByEmail(String email);
	List<UserAccount> getAllUserAccounts();
}
