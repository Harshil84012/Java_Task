package com.demo.main.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.demo.main.dto.InputRequest;
import com.demo.main.model.Student;

public interface StudentService {

	Student getStudentById(Integer id);

	ResponseEntity<Student> deleteStudent(Integer id);

	List<Student> getAllStudents();

	String save(InputRequest<Student> request);

	String updateStudent(Integer id, @Valid String fn, InputRequest<Student> request);

}
