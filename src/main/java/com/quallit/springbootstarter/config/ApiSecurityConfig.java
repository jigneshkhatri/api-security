/**
 * 
 */
package com.quallit.springbootstarter.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.quallit.springbootstarter.config.interceptors.AuthInterceptor;

import liquibase.integration.spring.SpringLiquibase;

/**
 * @author jigs
 *
 */
@ComponentScan(basePackages = "com.quallit.apisecurity")
@EnableJpaRepositories(basePackages = "com.quallit.apisecurity.repositories")
@EntityScan(basePackages = "com.quallit.apisecurity.entities")
public class ApiSecurityConfig implements WebMvcConfigurer {

	@Autowired
	private AuthInterceptor authInterceptor;

	@Autowired
	private DataSource dataSource;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SpringLiquibase liquibase() {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setChangeLog("classpath:/db/changelog/db.changelog-master.xml");
		liquibase.setDataSource(dataSource);
		liquibase.setShouldRun(true);
		return liquibase;
	}
}
