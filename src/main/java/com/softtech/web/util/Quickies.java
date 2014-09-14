package com.softtech.web.util;

import java.util.ArrayList;
import java.util.List;

public class Quickies {

	public static <T> List<T> list(T ... t){
		List<T> retVal = new ArrayList<T>();
		
		for(T i : t){
			retVal.add(i);
		}
		
		return retVal;
	}
	
	public static boolean isNull(Object o){
		return o == null;
	}
	
	public static boolean notNull(Object o){
		return o != null;
	}
}
