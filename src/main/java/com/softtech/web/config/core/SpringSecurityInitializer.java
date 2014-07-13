package com.softtech.web.config.core;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(2)
public class SpringSecurityInitializer extends
		AbstractSecurityWebApplicationInitializer {
	
}