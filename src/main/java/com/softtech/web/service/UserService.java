package com.softtech.web.service;

import com.softtech.web.model.User;

public interface UserService {
	void addUser(User user);
	User findUser(int userId);
}
