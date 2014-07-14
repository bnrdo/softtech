package com.softtech.web.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.softtech.web.annotation.Production;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.softtech.web.dao")
@PropertySource("classpath:jdbc.properties")
@Production
public class ProductionConfig extends AbstractDataConfig {
	
	@Inject	private Environment env;
	
	public ProductionConfig() { }
	
	@Override
	@Bean
	public DataSource dataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));

		return dataSource;
		
	}
	
}