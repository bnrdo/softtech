package com.softtech.web.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.softtech.web.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
	
	/*List<Log> findById(Long id);
	
	List<Log> findById(Long id, Pageable pageable);
	
	List<Log> findByApi(String api);
	
	List<Log> findByApi(String api, Pageable pageable);
	
	List<Log> findByMessageDirection(String messageDirection);
	
	List<Log> findByMessageDirection(String messageDirection, Pageable pageable);
	
	List<Log> findByApplicationCode(String applicationCode);
	
	List<Log> findByApplicationCode(String applicationCode, Pageable pageable);*/
	
	UserAccount findById(int id);
	
	UserAccount findByUsername(String username);
	
	UserAccount findByEmail(String email);
	
	UserAccount findByPasswordResetToken(String token);
	
	Page<UserAccount> findAll(Pageable pageable);
	
	List<UserAccount> findAll();
	
}