package com.demo.main.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.main.security.model.CustomStudentDetail;
import com.demo.main.security.model.Student;
import com.demo.main.security.repository.StudentRepository;

@Service
public class CustomStudentDetailsService implements UserDetailsService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Student student = this.studentRepository.getByUsername(username);
		
		if(student == null) {
			throw new UsernameNotFoundException(username);
		}
		
		
		return new CustomStudentDetail(student);
	}

	
	

}
