package com.demo.main.security.service;

import java.util.List;

import com.demo.main.security.model.Student;

public interface StudentService {

	List<Student> listAll();

	Student savestudent(Student student);

	Student getStudentById(Integer id);

	Student updateStudent(Student student, Integer id);

	void deleteById(Integer id);

}
