package com.demo.otm.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import com.demo.otm.dto.StudentDTO;
import com.demo.otm.exception.ResourceNotFoundException;
import com.demo.otm.model.Student;
import com.demo.otm.service.StudentService;

@ExtendWith(MockitoExtension.class)
public class Controller_Student {

	@Mock
	StudentService studentService;

	@InjectMocks
	StudentController studentcontroller;

	@Test
	public void test_getAllStudent() {

		// Request
		List<Student> studentList = new ArrayList<Student>();
		studentList.add(new Student(0, "harshil", "panchal", null));

		// mocking
		when(studentService.getAllStudent()).thenReturn(studentList);

		List<Student> student = studentcontroller.getAllStudent();

		verify(studentService, times(1)).getAllStudent();

		assertEquals(student, studentList);

	}

	@Test
	void test_getStudentById() {

		// mock

		Student mockstudent = new Student(1, "harshil", "panchal", "h@gmail.com");

		// request
		int Studentid = 4;

		System.out.println(mockstudent.getId());

		when(studentService.getStudenById(Studentid)).thenReturn(mockstudent);

		Student student = studentcontroller.getStudentById(Studentid);

		Mockito.verify(studentService, times(1)).getStudenById(Studentid);

		assertEquals(student, mockstudent);

	}

	@Test
	void test_addstudent() {

		// mock
		Student student = new Student(4, "mohit", "vaghasiya", "m@gmail.com");

		// mocking
		when(studentService.addstudent(student)).thenReturn(student);

		System.out.println(student.getFirstname());

		// test
		Student student1 = studentcontroller.addStudent(student);

		Mockito.verify(studentService, times(1)).addstudent(student1);
		// assertion
		System.out.println(student.getFirstname());
		Assertions.assertThat(student.getId()).isNotEqualTo(null);
		assertEquals(student1, student);
	}

	@Test
	void test_updateStudent() {

		// mock
		Student student = new Student(1, "harshil", "panchal", "h@gmail.com");

		// request
		int update_id = 5;

		// mocking
		when(studentService.updateStudent(student, update_id)).thenReturn(student);
		Student response = studentcontroller.updateStudent(student, update_id);

		student.setFirstname("pratik");
		student.setLastname("patel");

		System.out.println(student.getLastname());

		Mockito.verify(studentService, times(1)).updateStudent(student, update_id);
		Assertions.assertThat(student.getId()).isNotEqualTo(update_id);
		assertEquals(response, student);

	}

	@Test
	void getUserByIdNotFoundTest() {

		// Mocks
		Student Student = new Student(2, "kishan", "panchal", "k@gmail.com");

		System.out.print(Student.getId());

		// Request
		Integer mockUSerId = 2;

		ResourceNotFoundException expeactedResponce = new ResourceNotFoundException("id not found");

		// Mocking
		when(studentService.getStudenById(mockUSerId)).thenThrow(expeactedResponce);

		// Test
		ResourceNotFoundException actualResponce = assertThrows(ResourceNotFoundException.class,
				() -> studentcontroller.getStudentById(mockUSerId));

		// Assertion
		Mockito.verify(studentService, times(1)).getStudenById(mockUSerId);
		Assertions.assertThat(expeactedResponce.getMessage()).isEqualTo(actualResponce.getMessage());
	}

	@Test
	void test_deleteStudent() {

		// request
		int deleteid = 2;

		// mocking
		doNothing().when(studentService).deleteStudent(deleteid);

		// test
		studentcontroller.deleteStudent(deleteid);

		Mockito.verify(studentService, times(1)).deleteStudent(deleteid);

	}

}
