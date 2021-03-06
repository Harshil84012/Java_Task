package com.demo.main.oto.service;

import static org.junit.jupiter.api.Assertions.*;

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

import com.demo.main.oto.exception.ResourceNotFoundException;
import com.demo.main.oto.model.Student;
import com.demo.main.oto.model.StudentProfile;
import com.demo.main.oto.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)

class StudentServiceTest {
	@Mock
	StudentRepository studentRepository;

	@InjectMocks
	StudentServiceImpl studentServiceImpl;

	public List<Student> getAllStudent;

	public List<Student> getAllOrganization;

	@Test
	void test_listAll() {

		// request
		int mockid = 1;

		// mock
		StudentProfile studentprofile = new StudentProfile(mockid, "chandlodia", "1234");
		List<Student> mockstudent = new ArrayList<Student>();
		mockstudent.add(new Student(mockid, "firstname", "lastname", studentprofile));

		// mocking

		when(studentRepository.findAll()).thenReturn(mockstudent);

		// assert

		List<Student> student = studentServiceImpl.getAllStudent();

		Mockito.verify(studentRepository, times(1)).findAll();

		// Mockito.verify(studentRepository, times(1)).findAll();
		assertEquals(mockstudent, student);

	}

	@Test
	void getStudentByIdTest() {

		// Request
		int studentid = 3;

		// Mocks

		StudentProfile studentProfile = new StudentProfile(studentid, "chandlodia", "358632624");

		Student mockstudent = new Student(studentid, "harhsil", "panchal", studentProfile);

		// Mocking
		when(studentRepository.findById(studentid)).thenReturn(Optional.of(mockstudent));

		// Test
		Student student = studentServiceImpl.getStudentById(studentid);
		// Assertion
		verify(studentRepository, Mockito.times(1)).findById(studentid);
		assertEquals(mockstudent, student);

	}

	@Test
	void test_addStudent() {

		// mock

		int studentid = 3;

		StudentProfile studentProfile = new StudentProfile(studentid, "chandlodia", "358632624");

		Student mockstudent = new Student(studentid, "harhsil", "panchal", studentProfile);

		System.out.println(studentProfile.getAddress());
		when(studentRepository.save(mockstudent)).thenReturn(mockstudent);

		Student student = studentServiceImpl.addStudent(mockstudent);
		Mockito.verify(studentRepository, times(1)).save(student);
		// assert
		assertEquals(student, mockstudent);

	}

	@Test
	void updateStudentByidFailedStudentNotFound() {
		// Request
		int studentid = 3;

		StudentProfile studentProfile = new StudentProfile(studentid, "chandlodia", "358632624");

		Student mockstudent = new Student(studentid, "harhsil", "panchal", studentProfile);

		when(studentRepository.findById(studentid)).thenReturn(Optional.empty());

		// Test
		ResourceNotFoundException actualResponce = assertThrows(ResourceNotFoundException.class,
				() -> studentServiceImpl.updateStudent(studentid, mockstudent));

		// assert
		Mockito.verify(studentRepository, times(1)).findById(studentid);
		assertNotNull(actualResponce);
	}

	/*
	 * @Test void deleteStudentidnotfound() { // Request int studentid = 3;
	 * 
	 * StudentProfile studentProfile = new StudentProfile(studentid, "chandlodia",
	 * "358632624");
	 * 
	 * Student mockstudent = new Student(studentid, "harhsil", "panchal",
	 * studentProfile);
	 * 
	 * System.out.println(mockstudent.getFirstname());
	 * 
	 * when(studentRepository.findById(studentid)).thenReturn(Optional.empty());
	 * 
	 * // Test ResourceNotFoundException actualResponce =
	 * assertThrows(ResourceNotFoundException.class, () ->
	 * studentServiceImpl.deleteStudent(studentid));
	 * 
	 * // assert Mockito.verify(studentRepository, times(1)).deleteById(studentid);
	 * 
	 * assertNotNull(actualResponce); }
	 */
	@Test
	void updateStudentByIdPassed() {
		// Request
		int studentid = 3;

		StudentProfile studentProfile = new StudentProfile(studentid, "chandlodia", "358632624");

		Student mockstudent = new Student(studentid, "harhsil", "panchal", studentProfile);

		// Mock
		StudentProfile studentProfile1 = new StudentProfile(studentid, "chandlodia", "358632624");

		Student dbstudent = new Student(studentid, "harhsil", "panchal", studentProfile);

		// Mockingpela optional hatu
		when(studentRepository.findById(studentid)).thenReturn((Optional.of(dbstudent)));
		when(studentRepository.save(dbstudent)).then(i -> i.getArgument(0));

		// Test
		Student actualStudent = (Student) studentServiceImpl.updateStudent(studentid, mockstudent);

		// assert
		Mockito.verify(studentRepository, times(1)).findById(studentid);
		Mockito.verify(studentRepository, times(1)).save(dbstudent);
		assertEquals(dbstudent, actualStudent);
	}

	@Test
	void emptylistfound() {

		// mock
		StudentProfile studentprofile = new StudentProfile(1, "chandlodia", "1234");
		List<Student> mockstudent = new ArrayList<Student>();
		mockstudent.add(new Student(1, "harshil", "panchal", studentprofile));
		mockstudent.add(new Student(2, "mohit", "vaghasiya", studentprofile));

		when(studentRepository.findAll()).thenReturn(mockstudent);

		// assert

		List<Student> student = studentServiceImpl.getAllStudent();

		Mockito.verify(studentRepository, times(1)).findAll();

		// Mockito.verify(studentRepository, times(1)).findAll();
		assertEquals(2, mockstudent.size());

	}

	@Test
	void test_deleteStudent() {

		// request
		int deleteid = 1;

		//mocks
		StudentProfile studentProfile = new StudentProfile(deleteid, "chandlodia", "358632624");

		Student student = new Student(deleteid, "harshil", "panchal", studentProfile);
		
		
		// mocking
		when(studentRepository.findById(deleteid)).thenReturn(Optional.of(student));
		doNothing().when(studentRepository).delete(student);
		
		//test
		studentServiceImpl.deleteStudentById(deleteid);

		//assertion
		Mockito.verify(studentRepository, times(1)).delete(student);

	}

}
