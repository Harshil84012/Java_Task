package com.demo.main.security.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.main.security.exception.ResourceNotFoundException;
import com.demo.main.security.model.Student;
import com.demo.main.security.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<Student> listAll() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	@Override
	public Student savestudent(Student student) {
		// TODO Auto-generated method stub
		student.setPassword(passwordEncoder.encode(student.getPassword()));
		return studentRepository.save(student);
	}

	@Override
	public Student getStudentById(Integer id) {
		// TODO Auto-generated method stub
		return studentRepository.getById(id);
	}

	@Override
	public Student updateStudent(Student student, Integer id) {
		// TODO Auto-generated method stub

		Student estudent = getUserByIdRequired(id);
		estudent.setUsername(student.getUsername());
		estudent.setPassword(student.getPassword());
		estudent.setEmail(student.getEmail());
		return studentRepository.save(estudent);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	private Student getUserByIdRequired(Integer id) {
		return studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not fund with id " + id));
	}

}
