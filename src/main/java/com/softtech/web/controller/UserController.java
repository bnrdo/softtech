package com.softtech.web.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.softtech.web.model.User;
import com.softtech.web.model.UserAccount;
import com.softtech.web.service.UserAccountService;
import com.softtech.web.service.UserService;
import com.softtech.web.util.PasswordUtil;

@Controller
public class UserController {
	Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private UserService userService;
	
	@RequestMapping()
	public String createUserAccountSetup(){
		return "";
	}
	@RequestMapping()
	public String createUserAccount(@ModelAttribute UserAccount userAccount, BindingResult result, @RequestParam String action, Map<String, Object> map){
		if(userAccount!=null){
			try {
				User freshUser = new User();
				userService.addUser(freshUser);
				userAccount.setPassword(PasswordUtil.generateDefaultPassword());
				userAccount.setOwner(freshUser);
				userAccountService.addUserAccount(userAccount);			
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			userAccountService.addUserAccount(userAccount);
		}
		return "";
	}
}
