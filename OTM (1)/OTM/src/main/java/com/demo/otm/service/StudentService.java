package com.demo.otm.service;

import java.util.List;
import com.demo.otm.model.Student;

public interface StudentService {


	Student addstudent(Student student);

	Student getStudenById(Integer id);

	Student updateStudent(Student student, Integer id);

	void deleteStudent(Integer id);

	

	List<Student> getAllStudent();

	




}
