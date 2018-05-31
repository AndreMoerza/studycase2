package com.mitrais.studycase2.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// add a reference to our data source
	
	@Autowired
	private DataSource myDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(myDataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/book/showForm*").hasAnyRole("MANAGER", "ADMIN")
		.antMatchers("/book/save*").hasAnyRole("MANAGER", "ADMIN")
		.antMatchers("/book/delete").hasRole("ADMIN")
		.antMatchers("/book/**").hasRole("EMPLOYEE")
		.antMatchers("/resources/**").permitAll()
		.and()
		.formLogin()
			.loginPage("/showMyLoginPage")
			.loginProcessingUrl("/authenticateTheUser")
			.permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/access-denied");
	}
	
	@Bean
	public UserDetailsManager userDetailsManager() {
		
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		
		jdbcUserDetailsManager.setDataSource(myDataSource);
		
		return jdbcUserDetailsManager; 
	}
}
