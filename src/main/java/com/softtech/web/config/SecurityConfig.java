package com.softtech.web.config;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private @Inject UserDetailsService userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new ShaPasswordEncoder(256));
	}
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/css/**", "/fonts/**", "/img/**", "/js/**", "/", "/regist**", 
						"/process**","/show**", "/forgotPassword", "/about", "/contact", 
						"/reset", "/resetRevert", "/sendReset").permitAll()
				.antMatchers("/employer**/**").permitAll()
				.antMatchers("/recruiter**/**").permitAll()
				.antMatchers("/admin**/**").hasAnyRole("ADMIN", "SUPERVISOR", "USER")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/signin")
				.defaultSuccessUrl("/")
				.failureUrl("/signin?error=1")
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/");
 
	}
	
}