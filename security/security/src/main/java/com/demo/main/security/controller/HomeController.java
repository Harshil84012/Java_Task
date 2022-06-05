package com.demo.main.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public")
public class HomeController {

	@GetMapping("/home")
	public String home() {
		return "this is home page";
	}

	@GetMapping("/login")
	public String login() {
		return "this is login page";
	}
	@GetMapping("/register")

	public String register() {
		return "this is register page";
	}

}
