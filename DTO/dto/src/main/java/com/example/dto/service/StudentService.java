package com.example.dto.service;

import java.util.List;
import java.util.Optional;

import com.example.dto.model.Student;
import com.example.dto.rr.StudentRequest;
import com.example.dto.rr.StudentResponse;

public interface StudentService {

	List<StudentResponse> getAllStudent();

	StudentResponse addStudent(StudentRequest studentRequest);


	void DeleteStudentById(Integer id,Student student);

	StudentResponse updateStudent(Integer id, StudentRequest studentRequest);

	Student getStudentById(Integer id);

}
