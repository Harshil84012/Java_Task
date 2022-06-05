package com.demo.main.junit.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.main.junit.model.Student;
import com.demo.main.junit.service.StudentService;

@RequestMapping("/student")
public class Controller {

	@Autowired
	private StudentService studentService;

	@GetMapping("/getdata")
	public List<Student> listAll() {
		return studentService.listAll();

	}

	@PostMapping("/adddata")
	public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {
		studentService.save(student);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(student.getId())
				.toUri();
		System.out.println("added");
		return ResponseEntity.created(location).build();
	}


}
