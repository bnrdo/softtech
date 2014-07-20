package com.softtech.web.util;

import java.text.DecimalFormat;
import java.util.Date;

public class GenericUtils {
	public static String getDefaultUsernameInEmail(String email){
		String uniqueNumber = "_" + new DecimalFormat("000000000000000").format((new Date()).getTime());
		return email!=null ? email.split("@")[0] + uniqueNumber : null;
	}
}
