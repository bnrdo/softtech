package com.softtech.web.service.impl;

import java.util.Date;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.softtech.web.model.User;
import com.softtech.web.model.UserAccount;
import com.softtech.web.model.UserStatus;
import com.softtech.web.service.MailService;
import com.softtech.web.service.UserAccountService;
@Service
public class MailServiceImpl implements MailService {
	@Inject
	private JavaMailSender javaMailSender;
	@Inject
	private String genericFromAddress;
	@Inject
	private String genericFromName;
	@Inject
	private String genericSubject;
	@Inject
	private String genericDefaultTo;
	@Inject
	private String appLink;
	@Inject 
	private SpringTemplateEngine templateEngine;
	@Inject
	private UserAccountService userAccountService;

	@Override
	public boolean sendSimpleMail(String senderEmail, String senderName, String msg) {
		boolean success = false;
		try{
			final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
			mimeMessageHelper.setFrom(genericFromAddress, genericFromName);
			mimeMessageHelper.setTo(genericDefaultTo);
			mimeMessageHelper.setSubject(genericSubject+senderName);
			mimeMessageHelper.setReplyTo(senderEmail);
			mimeMessageHelper.setText(msg, true);
			javaMailSender.send(mimeMessage);
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return success;
	}
	
	@Override
	public boolean sendContactUsMail(String senderEmail, String senderName, String msg) {
		final Context ctx = new Context();
		ctx.setVariable("name", senderName);
		ctx.setVariable("message", msg);		
		boolean sendContact = send(ctx, "contact-mail.html", senderEmail, senderName, null);	
		
		final Context ctx2 = new Context();
		ctx2.setVariable("name", senderName);
		boolean sendAutoReply = send(ctx2, "contact-auto-reply-mail.html", genericFromAddress, genericFromName, senderEmail);	
		
		return sendContact && sendAutoReply;
	}
	
	@Override
	public boolean sendPasswordResetMail(UserAccount userAccount) {
		try{
			if(userAccount!=null){
				User user = userAccount.getOwner();
				if(user!=null){
					final Context ctx = new Context();
					if(StringUtils.isNotBlank(user.getFullName())){
						ctx.setVariable("name", user.getFullName());
					}				
					String token = userAccount.getPasswordForReset();
					String resetLink = appLink + "/reset?o=" + token;	
					String revertLink = appLink + "/resetRevert?&o=" + token;	
					ctx.setVariable("passwordResetLink", resetLink);	
					ctx.setVariable("revertLink", revertLink);			
					boolean success = send(ctx, "password-reset-mail.html", genericFromAddress, genericFromName, userAccount.getEmail());
					if(success){
						userAccount.setPasswordResetToken(token);
						userAccount.setPasswordResetSentDate(new Date());	
						userAccount.setStatus(UserStatus.INACTIVE);
						userAccountService.updateUserAccount(userAccount);
					}				
					return success;
				}				
			}	
		}catch(Exception e){
			e.printStackTrace();
		}		
		return false;
	}
	
	@Override
	public UserAccount sendRegistrationMail(UserAccount userAccount) {
		try{
			if(userAccount!=null){
				final Context ctx = new Context();
				String token = userAccount.getPasswordForReset();
				String resetLink = appLink + "/reset?o=" + token;					
				ctx.setVariable("passwordResetLink", resetLink);		
				send(ctx, "new-account-mail.html", genericFromAddress, genericFromName, userAccount.getEmail());
				userAccount.setPasswordResetToken(token);
				userAccount.setPasswordResetSentDate(new Date());				
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		return userAccount;
	}
	
	private boolean send(Context ctx, String template, String senderEmail, String senderName, String to) {
		boolean success = false;
		try{
			final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
			mimeMessageHelper.setFrom(genericFromAddress, genericFromName);
			if(StringUtils.isNotBlank(to)){
				mimeMessageHelper.setTo(to);
			}else{
				mimeMessageHelper.setTo(genericDefaultTo);
			}		
			mimeMessageHelper.setSubject(genericSubject+senderName);
			mimeMessageHelper.setReplyTo(senderEmail);		
			
			// Create the HTML body using Thymeleaf
			final String htmlContent = this.templateEngine.process(template, ctx);
			mimeMessageHelper.setText(htmlContent, true);
			
			javaMailSender.send(mimeMessage);
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return success;		
	}
	
	@Override
	public void sendMail(String from, String to, String subject, String msg) {
		// TODO Auto-generated method stub
		
	}

}
