package com.softtech.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("geng").password("1234").roles("USER");
		auth.inMemoryAuthentication().withUser("joseph").password("1234").roles("ADMIN");
	}
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
				.anyRequest().authenticated()
				.antMatchers("/admin/**").access("hasRole('ADMIN')")
				.and()
			.formLogin()
				.loginPage("/signin")
				.defaultSuccessUrl("/")
				.failureUrl("/signin?error=1")
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/");
 
	}
	
}