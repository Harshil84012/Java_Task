package com.demo.controller;

import java.util.List;

import java.util.NoSuchElementException;

import javax.validation.Valid;

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

import com.demo.Service.StudentService;
import com.demo.model.Student;

@RestController
@RequestMapping("/student")
public class Controller {

	@Autowired
	private StudentService studentService;

	@GetMapping("/getdata")
	public List<Student> getStudent() {
		return this.studentService.getAllStudents();
	}

	@PostMapping("/adddata")
	public String add(@Valid @RequestBody Student student) {
		return studentService.save(student);
	}

	@GetMapping("/{id}")
	public Student getStudentById(@Valid @PathVariable Integer id) {
		return this.studentService.getStudentById(id);

	}

	@PutMapping("/update/{id}")
	public String updateStudent(@Valid @RequestBody Student student, @PathVariable Integer id) {
		return this.studentService.updateStudent(id, student);

	}

	@DeleteMapping("/{id}")
	public String deleteStudent(@Valid @PathVariable Integer id) {
		return this.studentService.deleteStudent(id);

	}

}
