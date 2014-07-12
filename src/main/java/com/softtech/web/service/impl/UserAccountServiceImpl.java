package com.softtech.web.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.web.dao.UserAccountDao;
import com.softtech.web.model.UserAccount;
import com.softtech.web.service.UserAccountService;
@Service
public class UserAccountServiceImpl implements UserAccountService {
	@Autowired
	private UserAccountDao userAccountDao;
	
	@Transactional
	public void addUserAccount(UserAccount userAccount) {
		userAccountDao.addUserAccount(userAccount);
	}
	@Transactional
	public UserAccount findUserAccountByName(String username) {
		return userAccountDao.findUserAccountByName(username);
	}
	@Transactional
	public UserAccount findUserAccountByEmail(String email) {
		return userAccountDao.findUserAccountByEmail(email);
	}

}
