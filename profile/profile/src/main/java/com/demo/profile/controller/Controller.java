package com.demo.profile.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@Value("${my.channel.name}")
	private String mychannelname;

	@GetMapping("getall")
	public String welcome() {
		return "welcome home "+mychannelname;	
	}
}
