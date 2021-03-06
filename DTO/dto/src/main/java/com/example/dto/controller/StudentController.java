package com.example.dto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.model.Student;
import com.example.dto.rr.StudentRequest;
import com.example.dto.rr.StudentResponse;
import com.example.dto.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/getAll")
	public List<StudentResponse> getAllStudent(){
		return this.studentService.getAllStudent();
	}
	
	@PostMapping("/addStudent")
	public ResponseEntity<StudentResponse> addStudent(@RequestBody StudentRequest studentRequest){
		StudentResponse obj=this.studentService.addStudent(studentRequest);
		return new ResponseEntity<>(obj,HttpStatus.CREATED);
		
	}
	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable Integer id) {
		return studentService.getStudentById(id);
	}
	
	@DeleteMapping("/{id}")
	public String DeleteStudentById(@PathVariable Integer id,Student student) {
		this.studentService.DeleteStudentById(id,student);
		return "student deleted Successfully";
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<StudentResponse> updateStudent(@PathVariable Integer id,@RequestBody StudentRequest studentRequest){
		StudentResponse obj=this.studentService.updateStudent(id,studentRequest);
		return new ResponseEntity<>(obj,HttpStatus.CREATED);
	}
}
