package com.demo.main.junit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.main.junit.model.Student;
import com.demo.main.junit.repository.StudentRepository;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> listAll() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
		

	}

	@Override
	public void save(Student student) {
		// TODO Auto-generated method stub
		studentRepository.save(student);
	}

}
