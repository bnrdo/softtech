package com.softtech.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.web.model.Role;

@Controller
@RequestMapping("/")
public class MainController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		
		if(request.isUserInRole(Role.USER.getRoleName())) return "index";
		if(request.isUserInRole(Role.RECRUITER.getRoleName())) return "recruiter/index";
		if(internalUser(request)) return "admin/index";
		
		return "index";
		
	}

	private boolean internalUser(HttpServletRequest request) {
		return request.isUserInRole(Role.RECRUITER_INTERNAL.getRoleName()) || 
				request.isUserInRole(Role.SUPERVISOR.getRoleName()) || 
				request.isUserInRole(Role.ADMIN.getRoleName()) || 
				request.isUserInRole(Role.USER.getRoleName());
	}
	
}