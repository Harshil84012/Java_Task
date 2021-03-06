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
import com.demo.main.oto.model.StudentProfile;
import com.demo.main.oto.repository.StudentProfileRepository;

@ExtendWith(MockitoExtension.class)

class StudentProfileServiceImplTest {

	@Mock
	StudentProfileRepository studentProfileRepository;

	@InjectMocks
	StudentProfileServiceImpl studentProfileServiceImpl;

	List<StudentProfile> mystudent;

	StudentProfile studentProfile;

	/*
	 * @Test void test_mystudent() {
	 * 
	 * // mock List<StudentDTO> mystudent = new ArrayList<StudentDTO>();
	 * mystudent.add(new StudentDTO("harshil", "panchal")); mystudent.add(new
	 * StudentDTO("mohit", "vaghasiya"));
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

	// still got error

	@Test
	void test_getStudentProfileById() {

		// request
		int studentid = 1;

		// mock
		StudentProfile mockstudent = new StudentProfile(studentid, "chandlodia", "123456");

		// mocking
		when(studentProfileRepository.findById(studentid)).thenReturn(Optional.of(mockstudent));

		StudentProfile studentProfile = studentProfileServiceImpl.getStudentProfileById(studentid);
		verify(studentProfileRepository, times(1)).findById(studentid);
		// assert
		assertEquals(studentProfile, mockstudent);

	}

	@Test
	void getStudentProfileByIdFailedStudentNotFound() {

		// request
		int studentid = 1;

		// mocking
		when(studentProfileRepository.findById(studentid)).thenReturn(Optional.empty());

		// Test
		ResourceNotFoundException actualResponce = assertThrows(ResourceNotFoundException.class,
				() -> studentProfileServiceImpl.getStudentProfileById(studentid));

		// assert
		Mockito.verify(studentProfileRepository, times(1)).findById(studentid);
		assertNotNull(actualResponce);

	}

	@Test
	void test_addStudent() {

		// mock
		StudentProfile studentProfile = new StudentProfile(1, "chandlodia", "789654");

		// mocking
		when(studentProfileRepository.save(studentProfile)).thenReturn(studentProfile);

		StudentProfile studentProfile1 = studentProfileServiceImpl.addStudentProfile(studentProfile);
		Mockito.verify(studentProfileRepository, times(1)).save(studentProfile1);
		// assert
		assertEquals(studentProfile, studentProfile1);

	}

	@Test
	void test_deleteStudentProfile() {

		// Request
		Integer id = 3;

		// Mocking
		StudentProfile studentProfile = new StudentProfile(1, "chandlodia", "789654");

		when(studentProfileRepository.findById(id)).thenReturn(Optional.of(studentProfile));
		doNothing().when(studentProfileRepository).delete(studentProfile);

		// Test
		studentProfileServiceImpl.deleteStudentProfileById(id);
		// assert
		verify(studentProfileRepository, times(1)).delete(studentProfile);

	}

	@Test
	void getUserByIdNotFoundTest() {

		// Mocks
		StudentProfile studentProfile = new StudentProfile(1, "chandlodia", "789654");

		// Request
		Integer mockUSerId = 1;

		ResourceNotFoundException expeactedResponce = new ResourceNotFoundException("User Not Found With ID :");

		// Mocking
		when(studentProfileRepository.findById(mockUSerId)).thenThrow(expeactedResponce);

		// Test
		ResourceNotFoundException actualResponce = assertThrows(ResourceNotFoundException.class,
				() -> studentProfileServiceImpl.getStudentProfileById(mockUSerId));

		// Assertion
		Mockito.verify(studentProfileRepository, times(1)).findById(mockUSerId);
		assertEquals(expeactedResponce.getMessage(), actualResponce.getMessage());

	}

	@Test
	void updateStudentProfileByidFailedStudentNotFound() {
		// Request
		Integer studentid = 1;
		StudentProfile studentProfile = new StudentProfile(1, "chandlodia", "789654");

		when(studentProfileRepository.findById(studentid)).thenReturn(Optional.empty());

		// Test
		ResourceNotFoundException actualResponce = assertThrows(ResourceNotFoundException.class,
				() -> studentProfileServiceImpl.updateStudentProfile(studentid, studentProfile));

		// assert
		Mockito.verify(studentProfileRepository, times(1)).findById(studentid);
		assertNotNull(actualResponce);
	}

	@Test
	void updateStudentProfileByIdPassed() {
		// Request
		Integer studentid = 1;
		StudentProfile studentProfile = new StudentProfile(1, "chandlodia", "789654");

		// Mock
		StudentProfile studentProfile1 = new StudentProfile(2, "ghatlodia", "7896354");

		// Mocking
		when(studentProfileRepository.findById(studentid)).thenReturn(Optional.of(studentProfile1));
		when(studentProfileRepository.save(studentProfile1)).then(i -> i.getArgument(0));

		// Test
		StudentProfile actualStudent = (StudentProfile) studentProfileServiceImpl.updateStudentProfile(studentid,
				studentProfile1);

		// assert
		Mockito.verify(studentProfileRepository, times(1)).findById(studentid);
		Mockito.verify(studentProfileRepository, times(1)).save(studentProfile1);
		assertEquals(studentProfile1, actualStudent);
	}

	@Test
	void deleteuseridnotfound() {
		// Request
		Integer deletetid = 1;

		when(studentProfileRepository.findById(deletetid)).thenReturn(Optional.empty());

		// Test
		ResourceNotFoundException actualResponce = assertThrows(ResourceNotFoundException.class,
				() -> studentProfileServiceImpl.deleteStudentProfileById(deletetid));

		// assert
		Mockito.verify(studentProfileRepository, times(1)).findById(deletetid);
		assertNotNull(actualResponce);
	}

	
}
