package com.demo.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.jwt.model.JWTRequest;
import com.demo.jwt.model.JWTResponse;
import com.demo.jwt.service.UserService;
import com.demo.jwt.utility.JWTUtility;

@RestController
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private JWTUtility jwtUtility;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/")
	public String Home() {
		return "welcome to the login page";
	}

	@PostMapping("/authenticate")
	public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

		} catch (BadCredentialsException e) {
			throw new Exception("invailid credencial ", e);
		}
		final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());

		final String token = jwtUtility.generateToken(userDetails);
		return new JWTResponse(token);
	}
}
