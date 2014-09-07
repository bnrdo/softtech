package com.softtech.web.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softtech.web.service.MailService;

@Controller
public class ContactUsController {
	@Inject
	private MailService mailService;
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contactUs(){
		return "contactUs";
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String sendMessage(
		@RequestParam("senderName") final String senderName,
		@RequestParam("from") final String from,
		@RequestParam("message") final String message){
		if(mailService.sendContactUsMail(from, senderName, message)){
			return "redirect:/contact?success=1";	
		}else{
			return "redirect:/contact?error=1";	
		}		
	}
}
