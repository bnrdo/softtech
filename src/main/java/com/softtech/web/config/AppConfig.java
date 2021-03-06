package com.softtech.web.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.softtech.web.controller", excludeFilters = { @Filter(Configuration.class) })
@Import({ SecurityConfig.class, ServiceConfig.class, StandaloneDataConfig.class, ProductionDataConfig.class, MailConfig.class })
public class AppConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/signin").setViewName("login");
		registry.addViewController("/forgotPassword").setViewName("forgotPassword");
		registry.addViewController("/about").setViewName("aboutUs");
		registry.addViewController("/employer").setViewName("recruiter/index");
		registry.addViewController("/employer/postJob").setViewName("recruiter/postJob");
		registry.addViewController("/employer/requestPhoneScreen").setViewName("recruiter/requestPhoneScreen");
		registry.addViewController("/contact").setViewName("contactUs");
		registry.addViewController("/reset").setViewName("reset");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/img/**").addResourceLocations("/img/");
		registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");

	}

	@Bean
	public ServletContextTemplateResolver templateResolver() {

		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/views/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setOrder(2);

		return templateResolver;
	}
	
	@Bean
	public ClassLoaderTemplateResolver emailTemplateResolver() {

		ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
		emailTemplateResolver.setPrefix("mails/");
		emailTemplateResolver.setTemplateMode("HTML5");
		emailTemplateResolver.setCharacterEncoding("UTF-8");
		emailTemplateResolver.setOrder(1);

		return emailTemplateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {

		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		
		//templateEngine.setTemplateResolver(templateResolver());
		Set<ITemplateResolver> resolvers = new HashSet<ITemplateResolver>();
		resolvers.add(emailTemplateResolver());
		resolvers.add(templateResolver());
		
		templateEngine.setTemplateResolvers(resolvers);
		
		Set<IDialect> dialects = new HashSet<IDialect>(); 
		dialects.add(new SpringSecurityDialect());
		
		templateEngine.setAdditionalDialects(dialects);

		return templateEngine;
	}

	@Bean
	public ThymeleafViewResolver thymeleafViewResolver() {

		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setOrder(1);
		
		return viewResolver;
	}

	@Bean
    public MessageSource messageSource() {

		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(15);
        
        return messageSource;
        
    }

	@Bean
	public CommonsMultipartResolver multipartResolver(){
		
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(5 * 1024 * 1024); //5MB
		
		return multipartResolver;
		
	}
}