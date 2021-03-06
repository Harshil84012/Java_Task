package com.demo.otm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.demo.otm.exception.ResourceNotFoundException;
import com.demo.otm.model.Organization;
import com.demo.otm.model.Student;
import com.demo.otm.repository.OrganizationRepository;

@ExtendWith(MockitoExtension.class)
public class Service_Organization {

	@Mock
	OrganizationRepository organizationRepository;

	@InjectMocks
	OrganizationServiceImpl organizationServiceImpl;

	List<Organization> getAllOrganization;

	/*
	 * @Test public void test_listAll() {
	 * 
	 * // mock Student student = new Student(1, "harshil", "panchal",
	 * "h@gmail.com"); List<Organization> mystudent = new ArrayList<Organization>();
	 * mystudent.add(new Organization(1, "alpha", "private", student));
	 * 
	 * // mocking when(organizationRepository.findAll()).thenReturn(mystudent);
	 * 
	 * // assert
	 * 
	 * List<Organization> estudent = organizationServiceImpl.listAll();
	 * 
	 * Mockito.verify(organizationRepository, times(1)).findAll();
	 * 
	 * // Mockito.verify(studentRepository, times(1)).findAll();
	 * assertEquals(mystudent, estudent);
	 * 
	 * }
	 */

	@Test
	void test_getOrganizationById() {

		// request
		int organizationid = 1;

		// Request
		List<Student> studentList = new ArrayList<Student>();

		studentList.add(new Student(organizationid, "harhsil", "panchal", "h@gmail.com"));

		Organization mockorganization = new Organization(organizationid, "alpha", "private", studentList);
		// mock

		// mocking
		when(organizationRepository.findById(organizationid)).thenReturn(Optional.of(mockorganization));

		Organization organization = organizationServiceImpl.findOrganizationById(organizationid);
		Mockito.verify(organizationRepository, times(1)).findById(organizationid);

		// assert
		assertEquals(mockorganization, organization);

	}

	@Test
	void getOrganizationByIdFailedStudentNotFound() {

		// request
		int organizationid = 1;

		List<Student> studentList = new ArrayList<Student>();
		studentList.add(new Student(organizationid, "harshil", "panchal", "h@gmail.com"));
		Organization mockorganization = new Organization(organizationid, "alpha", "private", studentList);

		// mocking
		when(organizationRepository.findById(organizationid)).thenReturn(Optional.empty());

		// Test
		ResourceNotFoundException actualResponce = assertThrows(ResourceNotFoundException.class,
				() -> organizationServiceImpl.findOrganizationById(organizationid));

		// assert
		Mockito.verify(organizationRepository, times(1)).findById(organizationid);
		assertNotNull(actualResponce);

	}

	@Test
	void test_addStudent() {

		// mock

		int organizationid = 3;

		List<Student> studentList = new ArrayList<Student>();
		studentList.add(new Student(organizationid, "harhsil", "panchal", "h@gmail.com"));
		Organization mockorganization = new Organization(organizationid, "alpha", "private", studentList);
		// mocking
		when(organizationRepository.save(mockorganization)).thenReturn(mockorganization);

		Organization organization = organizationServiceImpl.saveOrganization(mockorganization);
		Mockito.verify(organizationRepository, times(1)).save(organization);
		// assert
		assertEquals(mockorganization, organization);

	}

	@Test
	void getOrgByIdTest() {

		// Request
		int organizationid = 3;

		// Mocks
		List<Student> userList = new ArrayList<Student>();
		userList.add(new Student(organizationid, "harhsil", "panchal", "h@gmail.com"));
		Organization mockOrganization = new Organization(organizationid, "Softvan", "private", userList);

		// Mocking
		when(organizationRepository.findById(organizationid)).thenReturn(Optional.of(mockOrganization));

		// Test
		Organization organization = organizationServiceImpl.findOrganizationById(organizationid);

		// Assertion
		verify(organizationRepository, Mockito.times(1)).findById(organizationid);
		assertEquals(mockOrganization, organization);

	}

	/*
	 * @Test void getOrgByIdNotFoundTest() {
	 * 
	 * int organizationid = 1; // Mocks List<Student> studentList = new
	 * ArrayList<Student>(); studentList.add(new Student(organizationid, "harhsil",
	 * "panchal", "h@gmail.com")); Organization mockorganization = new
	 * Organization(organizationid, "alpha", "private", studentList);
	 * 
	 * ResourceNotFoundException expeactedResponce = new
	 * ResourceNotFoundException("User Not Found With ID :");
	 * 
	 * // Mocking when(organizationRepository.findById(organizationid)).thenThrow(
	 * expeactedResponce);
	 * 
	 * // Test ResourceNotFoundException actualResponce =
	 * assertThrows(ResourceNotFoundException.class, () ->
	 * organizationServiceImpl.getOrganizationById(organizationid));
	 * 
	 * // Assertion Mockito.verify(organizationRepository,
	 * times(1)).findById(organizationid);
	 * assertEquals(expeactedResponce.getMessage(), actualResponce.getMessage());
	 * 
	 * }
	 */

	@Test
	void updateStudentByidFailedStudentNotFound() {
		// Request
		Integer organizationid = 1;
		List<Student> studentList = new ArrayList<Student>();
		studentList.add(new Student(organizationid, "harhsil", "panchal", "h@gmail.com"));
		Organization mockorganization = new Organization(organizationid, "alpha", "private", studentList);// if any
		when(organizationRepository.findById(organizationid)).thenReturn(Optional.empty());

		// Test
		ResourceNotFoundException actualResponce = assertThrows(ResourceNotFoundException.class,
				() -> organizationServiceImpl.updateOrganization(mockorganization, organizationid));

		// assert
		Mockito.verify(organizationRepository, times(1)).findById(organizationid);
		assertNotNull(actualResponce);
	}

	@Test
	void deleteOrganizationidnotfound() {
		// Request
		Integer organizationid = 1;

		List<Student> studentList = new ArrayList<Student>();

		studentList.add(new Student(organizationid, "harshil", "panchal", "h@gmail.com"));

		Organization organization = new Organization(organizationid, "harshil", "panchal", studentList);

		System.out.println(organization.getOrganizationname());

		when(organizationRepository.findById(organizationid)).thenReturn(Optional.empty());

		// Test
		ResourceNotFoundException actualResponce = assertThrows(ResourceNotFoundException.class,
				() -> organizationServiceImpl.deleteOrganization(organizationid));

		// assert
		Mockito.verify(organizationRepository, times(1)).findById(organizationid);
		assertNotNull(actualResponce);
	}

	@Test
	void updateOrganizationByIdPassed() {
		// Request

		int organizationid = 1;

		List<Student> studentList = new ArrayList<Student>();
		studentList.add(new Student(organizationid, "harshil", "harshil", "h@gmail.com"));
		Organization mockorganization = new Organization(organizationid, "alpha", "private", studentList);

		// Mock
		List<Student> studentList1 = new ArrayList<Student>();
		studentList.add(new Student(organizationid, "harshil", "harshil", "h@gmail.com"));
		Organization dborganization = new Organization(organizationid, "GIT", "selffinance", studentList1);

		// Mocking
		when(organizationRepository.findById(organizationid)).thenReturn((Optional.of(dborganization)));
		when(organizationRepository.save(dborganization)).then(i -> i.getArgument(0));

		// Test
		Organization actualStudent = (Organization) organizationServiceImpl.updateOrganization(mockorganization,
				organizationid);

		// assert
		Mockito.verify(organizationRepository, times(1)).findById(organizationid);
		Mockito.verify(organizationRepository, times(1)).save(dborganization);
		assertEquals(dborganization, actualStudent);
	}

}
