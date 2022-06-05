package com.example.dto.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.enums.CommonEnums;
import com.example.dto.exception.ResourceNotFoundException;
import com.example.dto.model.Student;
import com.example.dto.repository.StudentRepository;
import com.example.dto.rr.StudentRequest;
import com.example.dto.rr.StudentResponse;

@Service
public class StudentServiceImpl implements StudentService{
	

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	private StudentRequest convertStudenttoStudentRequest(Student student) {
		return modelMapper.map(student,StudentRequest.class);
	}

	private StudentResponse convertStudenttoStudentResponse(Student student) {
		return modelMapper.map(student,StudentResponse.class);
	}
	
	private Student convertStudentRequestToStudent(StudentRequest studentrequest) {
		return modelMapper.map(studentrequest, Student.class);
	}
	@Override
	public List<StudentResponse> getAllStudent() {
		
		return studentRepository.findAll().stream().map(this::convertStudenttoStudentResponse).collect(Collectors.toList());
	}

	@Override
	public StudentResponse addStudent(StudentRequest studentRequest) {
		
		Student student=convertStudentRequestToStudent(studentRequest);
		Student newstudent=studentRepository.save(student);
		return convertStudenttoStudentResponse(newstudent);
	}
	@Override
	public Student getStudentById(Integer id) {

		

		return studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(CommonEnums.EXCEPTION_MSG.getMessage()));
	}

	@Override
	public void DeleteStudentById(Integer id,Student student) {
		List<Student> studentList=(List<Student>) this.studentRepository.getById(id);
		student =studentList.get(0);
		student.setStatus(false);
		this.studentRepository.save(student);
		
	}

	@Override
	public StudentResponse updateStudent(Integer id, StudentRequest studentRequest) {
	
		Student estudent = this.studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(CommonEnums.EXCEPTION_MSG.getMessage()));
	estudent.setId(id);
	Student student=convertStudentRequestToStudent(studentRequest);
	Student newStudent=this.studentRepository.save(student);
		return convertStudenttoStudentResponse(newStudent);
	}

}
