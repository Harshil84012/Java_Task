package com.demo.swagger.service;

import java.util.List;

import com.demo.swagger.model.User;

public interface UserService {

	void addUser(User user);

	List<User> getAllUser();

	User updateUser(int id, User user);

	void deleteUser(int id);

	User getUserById(Integer id);

}
