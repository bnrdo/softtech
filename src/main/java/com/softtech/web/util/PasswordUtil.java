package com.softtech.web.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PasswordUtil {
	
	public static String generateDefaultPassword() throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		String text = new SimpleDateFormat("MMddyyyyHHmmssSSS").format(new Date());

		md.update(text.getBytes("UTF-8")); // Change this to "UTF-16" if needed
		byte[] digest = md.digest();
		BigInteger bigInt = new BigInteger(1, digest);
		String output = bigInt.toString(16);
		return output;
	}	
	public static String hashPassword(String password) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes("UTF-8")); // Change this to "UTF-16" if needed
		byte[] digest = md.digest();
		BigInteger bigInt = new BigInteger(1, digest);
		String output = bigInt.toString(16);
		return output;
	}	
	
}