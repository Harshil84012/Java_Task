package com.demo.JDBC.dao;

import java.util.List;

import com.demo.JDBC.model.User;

public interface UserRepository {

	User saveUser(User user);

	User updateUser(User user);

	User getUser(int id);

	List<User> getAllUser();

	String deleteUser(int id);

}
