package com.demo.swagger.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.swagger.model.User;
import com.demo.swagger.service.UserService;

@RestController
@RequestMapping("/api")
public class Controller {

	@Autowired
	private UserService userService;

	@PostMapping("/adduser")
	// @ApiOperation(value = "save user")
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}

	@GetMapping("/getAlluser")
	// @ApiOperation(value = "get all user")
	public List<User> getAllUser() {
		return userService.getAllUser();

	}

	@GetMapping("/{id}")
	// @ApiOperation(value = "get user by id")
	public User getUserById(@PathVariable int id) {
		return userService.getUserById(id);
	}

	@PutMapping("/{id}")
	// @ApiOperation(value = "update user")
	public User updateUser(@PathVariable int id, @RequestBody User user) {
		return userService.updateUser(id, user);
	}

	@DeleteMapping("{id}")
	// @ApiOperation(value = "delete user")
	public String deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return "deleted successfully";
	}
}
