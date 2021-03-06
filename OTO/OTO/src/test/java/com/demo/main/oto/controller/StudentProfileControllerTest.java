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

import com.demo.main.oto.exception.ResourceNotFoundException;
import com.demo.main.oto.model.StudentProfile;
import com.demo.main.oto.service.StudentProfileService;

@ExtendWith(MockitoExtension.class)

class StudentProfileControllerTest {

	@Mock
	StudentProfileService studentProfileService;

	@InjectMocks
	StudentProfileController studentProfilecontroller;

	List<StudentProfile> getAllStudentProfile;
	List<StudentProfile> studentProfile;

	@Test
	void test_getAllStudentProfile() {

		// mock
		List<StudentProfile> studentProfile_obj = new ArrayList<StudentProfile>();
		studentProfile_obj.add(new StudentProfile(1, "chandlodia", "123456"));
		studentProfile_obj.add(new StudentProfile(2, "ghatlodia", "789123"));

		// mocking
		when(studentProfileService.getAllStudentProfile()).thenReturn(studentProfile_obj);

		List<StudentProfile> studentProfile = studentProfileService.getAllStudentProfile();
		Mockito.verify(studentProfileService, times(1)).getAllStudentProfile();
		// assert
		assertEquals(studentProfile_obj, studentProfile);

	}

	@Test
	void test_getStudentProfileById() { // done

		// mock

		StudentProfile mockstudent = new StudentProfile(1, "chandlodia", "123456");

		// request
		int StudentProfileid = 4;

		System.out.println(mockstudent.getId());

		when(studentProfileService.getStudentProfileById(StudentProfileid)).thenReturn(mockstudent);

		StudentProfile student = studentProfilecontroller.getStudentById(StudentProfileid);

		Mockito.verify(studentProfileService, times(1)).getStudentProfileById(StudentProfileid);

		assertEquals(student, mockstudent);

	}

	@Test
	void test_addstudentProfile() {

		// mock
		StudentProfile mockstudent = new StudentProfile(1, "chandlodia", "123456");

		// mocking
		when(studentProfileService.addStudentProfile(mockstudent)).thenReturn(mockstudent);

		System.out.println(mockstudent.getPhonenumber());

		// test
		StudentProfile student1 = studentProfilecontroller.addStudent(mockstudent);
		Mockito.verify(studentProfileService, times(1)).addStudentProfile(student1);
		// assertion

		assertEquals(mockstudent, student1);
	}

	@Test
	void test_updateStudentProfile() {

		// request
		int update_id = 5;

		// mock
		StudentProfile mockstudent = new StudentProfile(1, "chandlodia", "123456");

		// mocking
		when(studentProfileService.updateStudentProfile(update_id, mockstudent)).thenReturn(mockstudent);
		StudentProfile response = studentProfilecontroller.updateStudent(update_id, mockstudent);

		mockstudent.setAddress("ghatlodia");
		mockstudent.setPhonenumber("785412");

		System.out.println(mockstudent.getAddress());

		Mockito.verify(studentProfileService, times(1)).updateStudentProfile(update_id, mockstudent);
		Assertions.assertThat(mockstudent.getId()).isNotEqualTo(update_id);
		assertEquals(response, mockstudent);

	}

	/*
	 * @Test void getEmptyListFailTest() {
	 * 
	 * // mock List<OrganizationDTO> organization_obj = new
	 * ArrayList<OrganizationDTO>(); organization_obj.add(new
	 * OrganizationDTO("alpha")); organization_obj.add(new OrganizationDTO("GIT"));
	 * 
	 * // mocking when(organizationService.listAll()).thenReturn(organization_obj);
	 * 
	 * List<OrganizationDTO> student = organizationService.listAll();
	 * Mockito.verify(organizationService, times(1)).listAll();
	 * 
	 * // assert assertEquals(2, organization_obj.size());
	 * 
	 * }
	 */

	@Test
	void getStudentProfileProfileByIdNotFoundTest() {

		// Mocks
		StudentProfile mockstudent = new StudentProfile(1, "chandlodia", "123456");

		// Request
		Integer mockStudentProfileId = 2;

		ResourceNotFoundException expeactedResponce = new ResourceNotFoundException("id not found");

		// Mocking
		when(studentProfileService.getStudentProfileById(mockStudentProfileId)).thenThrow(expeactedResponce);

		// Test
		ResourceNotFoundException actualResponce = assertThrows(ResourceNotFoundException.class,
				() -> studentProfilecontroller.getStudentById(mockStudentProfileId));

		// Assertion
		Mockito.verify(studentProfileService, times(1)).getStudentProfileById(mockStudentProfileId);
		Assertions.assertThat(expeactedResponce.getMessage()).isEqualTo(actualResponce.getMessage());
	}

	@Test
	void test_deleteStudentProfile() {

		// request
		int deleteid = 1;

		// test

		doNothing().when(studentProfileService).deleteStudentProfileById(deleteid);

		studentProfilecontroller.deleteStudentProfile(deleteid);

		Mockito.verify(studentProfileService, times(1)).deleteStudentProfileById(deleteid);

	}

}
