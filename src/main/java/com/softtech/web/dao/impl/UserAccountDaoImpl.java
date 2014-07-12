package com.softtech.web.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softtech.web.dao.UserAccountDao;
import com.softtech.web.model.UserAccount;
@Repository
public class UserAccountDaoImpl implements UserAccountDao {
	@Autowired
	private SessionFactory session;
	
	public void addUserAccount(UserAccount user) {
		session.getCurrentSession().save(user);
	}

	public void editUserAccount(UserAccount user) {
		session.getCurrentSession().update(user);
	}

	public void deleteUserAccount(int userId) {
		session.getCurrentSession().delete(findUserAccount(userId));
	}

	public UserAccount findUserAccount(int userId) {
		return (UserAccount) session.getCurrentSession().get(UserAccount.class, userId);
	}

	public UserAccount findUserAccountByName(String username) {
		Criteria criteria = session.getCurrentSession().createCriteria(UserAccount.class);
		criteria.add(Restrictions.eq("username", username));		
		return (UserAccount) criteria.uniqueResult();
	}
	public UserAccount findUserAccountByEmail(String email) {
		Criteria criteria = session.getCurrentSession().createCriteria(UserAccount.class);
		criteria.add(Restrictions.eq("email", email));		
		return (UserAccount) criteria.uniqueResult();
	}

	public List<UserAccount> getAllUserAccounts() {
		return session.getCurrentSession().createQuery("from UserAccount").list();
	}

}
