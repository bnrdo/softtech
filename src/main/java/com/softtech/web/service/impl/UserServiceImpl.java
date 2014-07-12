package com.softtech.web.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.web.dao.UserDao;
import com.softtech.web.model.User;
import com.softtech.web.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public void addUser(User user) {
		userDao.addUser(user);
	}
	@Transactional
	public User findUser(int userId) {
		return userDao.findUser(userId);
	}

}
