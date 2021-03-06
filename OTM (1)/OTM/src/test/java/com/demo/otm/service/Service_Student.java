package com.demo.otm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.demo.otm.dto.StudentDTO;
import com.demo.otm.exception.ResourceNotFoundException;
import com.demo.otm.model.Student;
import com.demo.otm.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
public class Service_Student {

	@Mock
	StudentRepository studentRepository;

	@InjectMocks
	StudentServiceImpl studentServiceImpl;

	@Mock
	ModelMapper modelMapper;

	List<StudentDTO> mystudent;

	Student student;

	/*
	 * @Test public void test_mystudent() {
	 * 
	 * // request
	 * 
	 * int id = 1;
	 * 
	 * // mock List<Student> mystudent = new ArrayList<Student>(); mystudent.add(new
	 * Student(id, "harshil", "panchal", "h@gmail.com")); mystudent.add(new
	 * Student(id, "mohit", "vaghasiya", "m@gmail.com"));
	 * 
	 * // mocking when(studentRepository.findAll()).thenReturn(mystudent);
	 * 
	 * // assert
	 * 
	 * List<StudentDTO> student = studentServiceImpl.listAll();
	 * 
	 * Mockito.verify(studentRepository, times(1)).findAll();
	 * 
	 * // Mockito.verify(studentRepository, times(1)).findAll();
	 * assertEquals(student, mystudent);
	 * 
	 * }
	 */
	@Test
	void test_getStudentById() {

		// request
		int studentid = 1;

		// mock
		Student mystudent = new Student(studentid, "harshil", "panchal", "k@gmail.com");

		// mocking
		when(studentRepository.findById(studentid)).thenReturn(Optional.of(mystudent));

		Student student = studentServiceImpl.getStudenById(studentid);
		verify(studentRepository, times(1)).findById(studentid);
		// assert
		assertEquals(mystudent, student);

	}

	@Test
	void getStudentByIdFailedStudentNotFound() {

		// request
		int studentid = 1;

		// mocking
		when(studentRepository.findById(studentid)).thenReturn(Optional.empty());

		// Test
		ResourceNotFoundException actualResponce = assertThrows(ResourceNotFoundException.class,
				() -> studentServiceImpl.getStudenById(studentid));

		// assert
		Mockito.verify(studentRepository, times(1)).findById(studentid);
		assertNotNull(actualResponce);

	}

	@Test
	void test_addStudent() {

		// mock
		Student student = new Student(1, "kishan", "panchal", "k@gmail.com");

		// mocking
		when(studentRepository.save(student)).thenReturn(student);

		Student student1 = studentServiceImpl.addstudent(student);
		Mockito.verify(studentRepository, times(1)).save(student1);
		// assert
		assertEquals(student1, student);

	}

	@Test
	void test_deleteStudent() {

		// Request
		Integer id = 3;

		// Mocking
		Student mockStudent = new Student(1, "harshil", "panchal", "h@gmail.com");

		when(studentRepository.findById(id)).thenReturn(Optional.of(mockStudent));
		doNothing().when(studentRepository).delete(mockStudent);

		studentServiceImpl.deleteStudent(id);

		// assert
		verify(studentRepository, times(1)).delete(mockStudent);

	}

	@Test
	void getUserByIdNotFoundTest() {

		// Mocks
		Student mockStudent = new Student(1, "harshil", "panchal", "h@gmail.com");

		// Request
		Integer mockUSerId = 1;

		ResourceNotFoundException expeactedResponce = new ResourceNotFoundException("User Not Found With ID :");

		// Mocking
		when(studentRepository.findById(mockUSerId)).thenThrow(expeactedResponce);

		// Test
		ResourceNotFoundException actualResponce = assertThrows(ResourceNotFoundException.class,
				() -> studentServiceImpl.getStudenById(mockUSerId));

		// Assertion
		Mockito.verify(studentRepository, times(1)).findById(mockUSerId);
		assertEquals(expeactedResponce.getMessage(), actualResponce.getMessage());

	}

	@Test
	void updateStudentByidFailedStudentNotFound() {
		// Request
		Integer studentid = 1;
		Student student = new Student(studentid, "harshil", "panchal", "h@gmail.com");

		when(studentRepository.findById(studentid)).thenReturn(Optional.empty());

		// Test
		ResourceNotFoundException actualResponce = assertThrows(ResourceNotFoundException.class,
				() -> studentServiceImpl.updateStudent(student, studentid));

		// assert
		Mockito.verify(studentRepository, times(1)).findById(studentid);
		assertNotNull(actualResponce);
	}

	@Test
	void updateStudentByIdPassed() {
		// Request
		Integer studentid = 1;

		Student student = new Student(studentid, "Mohit", "panchal", "h@gmail.com");

		// Mock
		Student dbstudent = new Student(studentid, "harshil", "panchal", "h@gmail.com");

		// Mocking
		when(studentRepository.findById(studentid)).thenReturn(Optional.of(dbstudent));
		when(studentRepository.save(dbstudent)).then(i -> i.getArgument(0));

		// Test
		Student actualStudent = studentServiceImpl.updateStudent(student, studentid);

		// assert
		Mockito.verify(studentRepository, times(1)).findById(studentid);
		Mockito.verify(studentRepository, times(1)).save(dbstudent);
		assertEquals(dbstudent, actualStudent);
	}

	@Test
	void deleteuseridnotfound() {
		// Request
		Integer deletetid = 1;
		Student student = new Student(deletetid, "harshil", "panchal", "h@gmail.com");
		System.out.println(student.getEmail());
		when(studentRepository.findById(deletetid)).thenReturn(Optional.empty());

		// Test
		ResourceNotFoundException actualResponce = assertThrows(ResourceNotFoundException.class,
				() -> studentServiceImpl.deleteStudent(deletetid));

		// assert
		Mockito.verify(studentRepository, times(1)).findById(deletetid);
		assertNotNull(actualResponce);
	}

}
