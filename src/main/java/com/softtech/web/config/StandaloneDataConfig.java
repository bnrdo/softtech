package com.softtech.web.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.softtech.web.annotation.Development;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.softtech.web.dao")
@Development
public class StandaloneDataConfig extends AbstractDataConfig {
	
	@Override
	@Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
            .build();
    }
	
	@Override
	protected boolean showSql() { return true; }
	
}