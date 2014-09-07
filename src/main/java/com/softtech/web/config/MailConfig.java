package com.softtech.web.config;

import java.util.Properties;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:mail.properties")
public class MailConfig {
	
	@Inject	private Environment env;
	
	@Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(env.getProperty("mail.host"));
        javaMailSender.setPort(Integer.parseInt(env.getProperty("mail.port")));
        javaMailSender.setUsername(env.getProperty("mail.username"));
        javaMailSender.setPassword(env.getProperty("mail.password"));
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        javaMailSender.setJavaMailProperties(props);
        return javaMailSender;
    }

    @Bean
    public SimpleMailMessage simpleMailMessage() {
       SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
       simpleMailMessage.setFrom(env.getProperty("mail.from"));
       simpleMailMessage.setSubject(env.getProperty("mail.subject"));
       return simpleMailMessage;
    }  
    
    @Bean
    public String genericSubject(){
    	return env.getProperty("mail.subject");
    }
    
    @Bean
    public String genericFromAddress(){
    	return env.getProperty("mail.from");
    }
    
    @Bean
    public String genericFromName(){
    	return env.getProperty("mail.from.name");
    }
    
    @Bean
    public String genericDefaultTo(){
    	return env.getProperty("mail.default.to");
    }
    
    @Bean
    public String appLink(){
    	return env.getProperty("mail.app.link");
    }
   
}
