package com.softtech.web.service;

import com.softtech.web.model.UserAccount;


public interface MailService {
	void sendMail(String from, String to, String subject, String msg);
	boolean sendContactUsMail(String senderEmail, String senderName, String msg);
	boolean sendSimpleMail(String senderEmail, String senderName, String msg);
	UserAccount sendRegistrationMail(UserAccount userAccount);
	boolean sendPasswordResetMail(UserAccount userAccount);
}
