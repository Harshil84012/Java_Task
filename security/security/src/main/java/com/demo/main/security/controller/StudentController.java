package com.demo.main.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.main.security.model.Student;
import com.demo.main.security.service.StudentService;

@RestController
@RequestMapping("/request")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@GetMapping("/hello")
	public String hello() {
		return "Welcome";
	}

	@GetMapping("/getAll")
	public List<Student> list() {

		return studentService.listAll();
	}

	@PostMapping("/addstudent")
	public Student addStudent(@RequestBody Student student) {
		return studentService.savestudent(student);
	}

	/* @PreAuthorize("hasRole('ADMIN')") */
	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable Integer id) {
		return this.studentService.getStudentById(id);

	}

	@PutMapping("/{id}")
	public Student updateStudent(@RequestBody Student student, @PathVariable Integer id) {

		return studentService.updateStudent(student, id);

	}

	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable Integer id) {

		studentService.deleteById(id);
		return "data deletedSuccessfully";

	}
}
