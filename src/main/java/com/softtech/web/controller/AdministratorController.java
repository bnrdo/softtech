package com.softtech.web.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.web.model.User;
import com.softtech.web.model.UserAccount;
import com.softtech.web.model.UserStatus;
import com.softtech.web.service.RoleService;
import com.softtech.web.service.UserAccountService;
import com.softtech.web.service.UserService;
import com.softtech.web.util.PasswordUtil;

@Controller
@RequestMapping("/admin")
public class AdministratorController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Inject
	private UserAccountService userAccountService;
	
	@Inject
	private RoleService roleService;
	
	@Inject
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String adminHome() {
		
		return "admin/index";
		
	}
	
	@RequestMapping(value = "/createUserAccount", method = RequestMethod.GET)
	public String createUserAccountSetup(Model model) {
		
		model.addAttribute("userAccount", new UserAccount());
		model.addAttribute("roles", roleService.getRolesForUserCreation());
		
		return "admin/createUserAccount";
		
	}
	
	@RequestMapping(value = "/createUserAccount", method = RequestMethod.POST)
	public String createUserAccount(UserAccount userAccount) {
		
		if(userAccount != null) {
			try {
				User freshUser = new User();
				userService.addUser(freshUser);
				userAccount.setPassword(PasswordUtil.generateDefaultPassword());
				userAccount.setOwner(freshUser);
				userAccount.setStatus(UserStatus.ACTIVE);
				userAccountService.addUserAccount(userAccount);			
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			
			userAccountService.addUserAccount(userAccount);
		}
		
		return "redirect:/admin/createUserAccount";
		
	}
	
}