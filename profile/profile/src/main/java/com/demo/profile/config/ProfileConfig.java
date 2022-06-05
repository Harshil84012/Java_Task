package com.demo.profile.config;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfileConfig {

	@Value(value = "${my.prop}")
	private String myprop;

	@Profile(value = { "default" })
	public String createBeanforDefault() {
		return myprop;
	}
	
	@Profile(value = { "dev" })
	public String createBeanforDev() {
		return myprop;
	}
	
	@Profile(value = { "prod" })
	public String createBeanforProd() {
		return myprop;
	}
}
