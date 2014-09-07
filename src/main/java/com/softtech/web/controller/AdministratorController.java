package com.softtech.web.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.web.model.User;
import com.softtech.web.model.UserAccount;
import com.softtech.web.model.UserStatus;
import com.softtech.web.service.MailService;
import com.softtech.web.service.RoleService;
import com.softtech.web.service.UserAccountService;
import com.softtech.web.service.UserService;
import com.softtech.web.util.GenericUtils;
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
	
	@Inject MailService mailService;
	
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
				if(userAccountService.findUserAccountByEmail(userAccount.getEmail())==null){
					User freshUser = new User();
					userService.addUser(freshUser);
					userAccount.setUsername(GenericUtils.getDefaultUsernameInEmail(userAccount.getEmail()));
					userAccount.setPassword(PasswordUtil.generateDefaultPassword());
					userAccount.setOwner(freshUser);
					userAccount.setStatus(UserStatus.INACTIVE);
					userAccountService.addUserAccount(userAccount);	
				}else{
					return "redirect:/admin/createUserAccount?accountExists=1";	
				}
						
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
				
		return "redirect:/admin/createUserAccount?success=1";		
	}
	
	@RequestMapping(value = "/userAccountList", method = RequestMethod.GET)
	public String listUserAccounts(Model model) {
		List<UserAccount> accounts = userAccountService.findAll();
		model.addAttribute("accounts", accounts);
		return "admin/userAccountList";
		
	}
	
	@RequestMapping(value = "/removeUserAccounts", method = RequestMethod.POST)
	public String removeUserAccounts(HttpServletRequest request) {
		String[] accountsToRemove = request.getParameterValues("accountsToRemove");	
		userAccountService.removeUserAccountsByEmail(accountsToRemove);
		return "redirect:/admin/userAccountList";		
	}
	
	@RequestMapping(value = "/editUserAccount", method = RequestMethod.POST)
	public String editUserAccounts(HttpServletRequest request, Model model) {
		String accountToUpdate = request.getParameter("email");	
		UserAccount account = userAccountService.findUserAccountByEmail(accountToUpdate);
		model.addAttribute("userAccount", account);
		model.addAttribute("userAssignedRoles", account.getRoles());
		model.addAttribute("userAccountCurrentStatus", account.getStatus().toString());
		model.addAttribute("roles", roleService.getRolesForUserCreation());		
		return "admin/editUserAccount";	
	}
	
	@RequestMapping(value = "/updateUserAccount", method = RequestMethod.POST)
	public String updateUserAccount(UserAccount userAccount) {		
		if(userAccount != null) {
			try {
				userAccountService.updateUserAccountStatusAndRoles(userAccount);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}		
		return "redirect:/admin/userAccountList";		
	}
	
}