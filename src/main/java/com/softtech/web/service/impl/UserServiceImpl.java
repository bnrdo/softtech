package com.softtech.web.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softtech.web.dao.UserRepository;
import com.softtech.web.model.User;
import com.softtech.web.service.UserService;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	
	@Inject
	private UserRepository userRepository;
	
	public UserServiceImpl() { }
	
	@Transactional(readOnly = false)
	@Override
	public void addUser(User user) {
		
		userRepository.save(user);
		
	}
	
	@Override
	public User findUser(int userId) {
		
		return userRepository.findOne(userId);
		
	}

}
