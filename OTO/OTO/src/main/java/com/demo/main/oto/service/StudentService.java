package com.demo.main.oto.service;

import java.util.List;

import com.demo.main.oto.model.Student;





public interface StudentService {

	List<Student> getAllStudent();

	Student addStudent(Student student);

	Student getStudentById(Integer id);

	Student updateStudent(Integer id, Student student);

	void deleteStudentById(Integer id);

	

}
