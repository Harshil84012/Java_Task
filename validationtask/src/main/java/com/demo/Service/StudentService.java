package com.demo.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.demo.model.Student;

public interface StudentService {

	List<Student> getAllStudents();

	String save(Student student);

	Student getStudentById(Integer id);

	String updateStudent(Integer id, Student student);

	String deleteStudent(Integer id);

	
	

}
