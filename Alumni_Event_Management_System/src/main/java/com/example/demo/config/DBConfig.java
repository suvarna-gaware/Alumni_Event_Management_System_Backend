package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DBConfig {
    
	@Bean(name="template")
   public JdbcTemplate getTemplate(DataSource datasource) {
	   return new JdbcTemplate(datasource);
   }
}
