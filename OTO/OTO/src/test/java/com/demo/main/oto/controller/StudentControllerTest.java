package com.demo.main.oto.controller;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.demo.main.oto.model.Student;
import com.demo.main.oto.model.StudentProfile;
import com.demo.main.oto.service.StudentService;

@ExtendWith(MockitoExtension.class)

class StudentControllerTest {

	@Mock
	StudentService studentService;

	@InjectMocks
	StudentController studentcontroller;

	List<Student> getAllOrganization;
	List<Student> organizationdto;

	@Test
	void test_getStudentById() {

		// request
		int studentid = 4;
		StudentProfile studentProfile = new StudentProfile(studentid, "chandlodia", "12345");
		Student mockstudent = new Student(studentid, "harshil", "panchal", studentProfile);
		when(studentService.getStudentById(studentid)).thenReturn(mockstudent);

		Student student = studentcontroller.getStudentById(studentid);

		Mockito.verify(studentService, times(1)).getStudentById(studentid);

		assertEquals(student, mockstudent);

	}

	@Test
	void test_addstudent() {

		int studentid = 3;
		StudentProfile studentProfile = new StudentProfile(studentid, "chandldia", "789654");
		Student mockstudent = new Student(studentid, "harshil", "panchal", studentProfile);

		// mocking
		when(studentService.addStudent(mockstudent)).thenReturn(mockstudent);

		// test
		Student organization = studentcontroller.addStudent(mockstudent);
		Mockito.verify(studentService, times(1)).addStudent(mockstudent);

		// assertion
		System.out.println(organization.getFirstname());

		assertEquals(organization, mockstudent);
	}

	@Test
	void test_updateOrganization() {

		// request
		int update_id = 5;

		StudentProfile studentProfile = new StudentProfile(update_id, "chandldia", "789654");
		Student mockstudent = new Student(4, "harshil", "panchal", studentProfile);

		// mocking
		when(studentService.updateStudent(update_id, mockstudent)).thenReturn(mockstudent);
		Student student = studentcontroller.updateStudent(update_id, mockstudent);

		Mockito.verify(studentService, times(1)).updateStudent(update_id, student);
		Assertions.assertThat(student.getId()).isNotEqualTo(update_id);
		assertEquals(student, mockstudent);

	}

	@Test
	void getEmptyListFailTest() {

		int mockid = 2;

		StudentProfile studentProfile = new StudentProfile(mockid, "chandlodia", "12345");
		List<Student> mockstudent = new ArrayList<Student>();
		mockstudent.add(new Student(mockid, "harshil", "panchal", studentProfile));
		mockstudent.add(new Student(mockid, "kishan", "panchal", studentProfile));

		when(studentService.getAllStudent()).thenReturn(mockstudent);

		List<Student> student = studentcontroller.getAllStudent();

		// assert
		assertEquals(2, mockstudent.size());

	}

	@Test
	void getStudentByIdNotFoundTest() {

		// request
		int studentid = 1;
		// mock

		StudentProfile studentProfile = new StudentProfile(studentid, "chandldia", "789654");
		Student mockstudent = new Student(4, "harshil", "panchal", studentProfile);

		// mock
		Student mockorganization = new Student(studentid, "alpha", "private", studentProfile);

		System.out.print(mockorganization.getId());

		// Mocking
		when(studentService.getStudentById(studentid)).thenReturn(mockorganization);

		// Test
		Student organization = studentcontroller.getStudentById(studentid);

		// Assertion
		Mockito.verify(studentService, times(1)).getStudentById(studentid);
		assertEquals(organization, mockorganization);

	}

	@Test
	void test_deleteStudent() {

		// request
		int deleteid = 1;

		// test

		doNothing().when(studentService).deleteStudentById(deleteid);

		studentcontroller.deleteStudent(deleteid);

		Mockito.verify(studentService, times(1)).deleteStudentById(deleteid);

	}

}
