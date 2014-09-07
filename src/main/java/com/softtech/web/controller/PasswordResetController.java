package com.softtech.web.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softtech.web.model.UserAccount;
import com.softtech.web.model.UserStatus;
import com.softtech.web.service.MailService;
import com.softtech.web.service.UserAccountService;
import com.softtech.web.util.PasswordUtil;

@Controller
public class PasswordResetController {
	@Inject
	private UserAccountService userAccountService;
	@Inject
	private MailService mailService;

	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public String getReset(@RequestParam("o") final String token, HttpServletRequest request){
		UserAccount userAccount = userAccountService.findUserAccountByPasswordResetToken(token);
		if(userAccount!=null){
			request.setAttribute("token", token);
			return "reset";
		}else{
			request.setAttribute("linkExpired", Boolean.TRUE);
			return "reset";
		}
	}
	
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public String doReset(
		@RequestParam("token") final String token,
		@RequestParam("password") final String password, HttpServletRequest request) throws Exception{
		
		UserAccount userAccount = userAccountService.findUserAccountByPasswordResetToken(token);
		
		if(userAccount!=null && !isLinkExpired(userAccount.getPasswordResetSentDate(), new Date())){
			userAccount.setPassword(PasswordUtil.hashPassword(password));
			userAccount.setPasswordResetToken(null);
			userAccount.setPasswordResetSentDate(null);
			userAccount.setStatus(UserStatus.ACTIVE);
			userAccountService.updateUserAccount(userAccount);
			return "redirect:/signin";
		}else{
			request.setAttribute("error", Boolean.TRUE);
			return "reset";
		}			
	}
	
	@RequestMapping(value = "/sendReset", method = RequestMethod.POST)
	public String doSendResetEmail(
		@RequestParam("email") final String email) throws Exception{		
		UserAccount userAccount = userAccountService.findUserAccountByEmail(email);		
		if(userAccount!=null && mailService.sendPasswordResetMail(userAccount)){			
			return "redirect:/forgotPassword?success=1";
		}else{
			return "redirect:/forgotPassword?notExists=1";
		}			
	}
	
	@RequestMapping(value = "/resetRevert", method = RequestMethod.GET)
	public String getResetRevert(@RequestParam("o") final String token, HttpServletRequest request){
		UserAccount userAccount = userAccountService.findUserAccountByPasswordResetToken(token);		
		if(userAccount!=null && !isLinkExpired(userAccount.getPasswordResetSentDate(), new Date())){
			userAccount.setPasswordResetToken(null);
			userAccount.setPasswordResetSentDate(null);
			userAccount.setStatus(UserStatus.ACTIVE);
			userAccountService.updateUserAccount(userAccount);
			request.setAttribute("successResetRevert", Boolean.TRUE);
			return "login";
		}else{
			return "redirect:/forgotPassword?linkExpired=1";
		}
	}	
	
	private boolean isLinkExpired(Date earlierDate, Date laterDate)
	{
	    int ageInMinutes = (int)((laterDate.getTime()/1000/60) - (earlierDate.getTime()/1000/60));
	    return ageInMinutes > 30;
	}
}
