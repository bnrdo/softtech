package com.softtech.web.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

public abstract class AbstractDataConfig {
	
	@Bean
	public EntityManagerFactory entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setShowSql(showSql());
		
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.softtech.web.model");
		factory.setJpaProperties(additionalProperties());
		factory.setDataSource(dataSource());
		factory.afterPropertiesSet();

		return factory.getObject();
		
	}

	@Bean
	public PlatformTransactionManager transactionManager() {

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		
		return txManager;
		
	}
	
	protected Properties additionalProperties() {
		
		Properties properties = new Properties();	
		properties.setProperty("hibernate.hbm2ddl.auto", "none");
		
		return properties;
		
	}
	
	public abstract DataSource dataSource();
	
	protected boolean showSql() { return false; }
	
}