package com.demo.main.oto.controller;

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

import com.demo.main.oto.model.Student;
import com.demo.main.oto.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/getAll")
	public List<Student> getAllStudent() {
		return studentService.getAllStudent();
	}

	@PostMapping("/addstudent")
	public Student addStudent(@RequestBody Student student) {
		return studentService.addStudent(student);
	}

	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable Integer id) {
		return studentService.getStudentById(id);
	}

	@PutMapping("/{id}")
	public Student updateStudent(@PathVariable Integer id, @RequestBody Student student) {
		return studentService.updateStudent(id, student);
	}

	@DeleteMapping("/{id}")
	public String deleteStudent(@PathVariable Integer id) {
		studentService.deleteStudentById(id);
		return "student deleted Successfully";
	}
}
