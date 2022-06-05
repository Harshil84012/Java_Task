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

import com.demo.main.oto.model.StudentProfile;
import com.demo.main.oto.service.StudentProfileService;


@RestController
@RequestMapping("/studentProfiile")
public class StudentProfileController {

	@Autowired
	private StudentProfileService studentProfileService;

	@GetMapping("/getAll")
	public List<StudentProfile> getAllStudent() {
		return studentProfileService.getAllStudentProfile();
	}

	@PostMapping("/addstudent")
	public StudentProfile addStudent(@RequestBody StudentProfile studentProfile) {
		return studentProfileService.addStudentProfile(studentProfile);
	}

	@GetMapping("/{id}")
	public StudentProfile getStudentById(@PathVariable Integer id) {
		return studentProfileService.getStudentProfileById(id);
	}

	@PutMapping("/{id}")
	public StudentProfile updateStudent(@PathVariable Integer id, @RequestBody StudentProfile studentProfile) {
		return studentProfileService.updateStudentProfile(id, studentProfile);
	}

	@DeleteMapping("/{id}")
	public String deleteStudentProfile(@PathVariable Integer id) {
		studentProfileService.deleteStudentProfileById(id);
		return "student deleted Successfully";
	}

}
