package com.softtech.web.dao;

import java.util.List;

import com.softtech.web.model.User;

public interface UserDao {
	void addUser(User user);
	void editUser(User user);
	void deleteUser(int userId);
	User findUser(int userId);
	List<User> getAllUsers();
}
