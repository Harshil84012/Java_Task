package com.demo.main.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.main.dto.InputRequest;
import com.demo.main.model.Student;
import com.demo.main.service.StudentService;
import java.util.List;
import javax.validation.Valid;

@Validated
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
	public String add(@Valid @RequestBody InputRequest<Student> student) {
		return studentService.save(student);
	}

	@GetMapping("/{id}")
	public Student getStudentById(@Valid @PathVariable Integer id) {
		return this.studentService.getStudentById(id);

	}

	@PutMapping("/update/{id}/{phno}")
	public String updateStudent(@Valid @RequestBody String fn, @PathVariable Integer id,
			@RequestBody InputRequest<Student> request) {
		return this.studentService.updateStudent(id, fn, request);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Student> deleteStudent(@Valid @PathVariable Integer id) {
		return this.studentService.deleteStudent(id);

	}

}
