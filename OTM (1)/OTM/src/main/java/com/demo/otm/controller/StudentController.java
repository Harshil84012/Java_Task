package com.demo.otm.controller;

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
import com.demo.otm.dto.StudentDTO;
import com.demo.otm.model.Student;
import com.demo.otm.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/allstudent")
	public List<Student> getAllStudent() {
		return studentService.getAllStudent();

	}

	@PostMapping("/addstudent")
	public Student addStudent(@RequestBody Student student) {
		return studentService.addstudent(student);
	}

	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable Integer id) {
		return studentService.getStudenById(id);

	}

	@PutMapping("/{id}")
	public Student updateStudent(@RequestBody Student student, @PathVariable Integer id) {
		return studentService.updateStudent(student, id);

	}

	@DeleteMapping("/{id}")
	public String deleteStudent(@PathVariable Integer id) {

		this.studentService.deleteStudent(id);
		return "student deleted successfully!";

	}

}
