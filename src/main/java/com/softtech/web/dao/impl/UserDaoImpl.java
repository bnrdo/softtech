package com.softtech.web.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softtech.web.dao.UserDao;
import com.softtech.web.model.User;
@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory session;
	
	public void addUser(User user) {
		session.getCurrentSession().save(user);
	}

	public void editUser(User user) {
		session.getCurrentSession().update(user);
	}

	public void deleteUser(int userId) {
		session.getCurrentSession().delete(findUser(userId));
	}

	public User findUser(int userId) {
		return (User)session.getCurrentSession().get(User.class, userId);
	}

	public List<User> getAllUsers() {
		return null;
	}

}
