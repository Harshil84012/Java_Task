package com.demo.main.security.config;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.demo.main.security.service.CustomStudentDetailsService;

@Configuration
@EnableWebSecurity
public class MyConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomStudentDetailsService customStudentDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				.csrf().disable().authorizeRequests().antMatchers("/signin").permitAll().antMatchers("/public/**")
				.hasRole("NORMAL").antMatchers("/student/**").hasRole("ADMIN").anyRequest().authenticated().and()
				.formLogin().loginPage("/signin").loginProcessingUrl("/dologin").defaultSuccessUrl("/request/getAll");

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");

		auth.userDetailsService(customStudentDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

}
