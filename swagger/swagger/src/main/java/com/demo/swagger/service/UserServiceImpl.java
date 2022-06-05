package com.demo.swagger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.swagger.exception.ResourceNotFoundException;
import com.demo.swagger.model.User;
import com.demo.swagger.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		return getUserByIdRequired(id);
	}

	@Override
	public User updateUser(int id, User user) {
		// TODO Auto-generated method stub
		User euser = getUserByIdRequired(id);
		euser.setName(user.getName());
		euser.setEmail(user.getEmail());
		euser.setPhone(user.getPhone());

		return userRepository.save(euser);
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}

	private User getUserByIdRequired(Integer id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));

	}
}
