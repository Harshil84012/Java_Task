package com.demo.otm.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.demo.otm.dto.OrganizationDTO;
import com.demo.otm.model.Organization;
import com.demo.otm.model.Student;
import com.demo.otm.service.OrganizationService;

@ExtendWith(MockitoExtension.class)
public class Controller_Organization {

	@Mock
	OrganizationService organizationService;

	@InjectMocks
	OrganizationController organizationcontroller;

	

	@Test
	void test_getAllOrgs() {

		// request
		int mockid = 1;

		// mock
		List<Student> student = new ArrayList<Student>();
		student.add(new Student(mockid, "harshil", "panchal", "h@gmail.con"));
		List<Organization> organization_obj = new ArrayList<Organization>();
		organization_obj.add(new Organization(mockid, "alpha", "private", student));

		// mocking
		when(organizationService.listAll()).thenReturn(organization_obj);

		List<Organization> organizationdto = organizationService.listAll();
		Mockito.verify(organizationService, times(1)).listAll(); // assert
		assertEquals(organization_obj, organizationdto);

	}

	@Test
	void test_getOrganizationById() {

		// request
		int organizationid = 4;

		// mock

		List<Student> studentList = new ArrayList<Student>();
		studentList.add(new Student(organizationid, "harshil", "panchal", "h@gmail.com"));
		Organization mockorganization = new Organization(organizationid, "alpha", "private", studentList);// if any

		System.out.println(mockorganization.getOrganizationname());

		when(organizationService.findOrganizationById(organizationid)).thenReturn(mockorganization);

		Organization organization = organizationcontroller.findOrganizationById(organizationid);

		Mockito.verify(organizationService, times(1)).findOrganizationById(organizationid);

		assertEquals(mockorganization, organization);

	}

	@Test
	void test_addstudent() {

		// Request
		List<Student> studentList = new ArrayList<Student>();

		// mocks

		studentList.add(new Student(1, "pratik", "patel", "p@gmail.com"));
		// mock
		Organization mockorganization = new Organization(1, "alpha", "private", studentList);

		// mocking
		when(organizationService.saveOrganization(mockorganization)).thenReturn(mockorganization);

		// test
		Organization organization = organizationcontroller.addOrganization(mockorganization);
		Mockito.verify(organizationService, times(1)).saveOrganization(organization);

		// assertion
		System.out.println(organization.getOrganizationname());

		assertEquals(organization, mockorganization);
	}

	@Test
	void test_updateOrganization() {

		// request
		int update_id = 5;

		// mocks

		List<Student> studentList = new ArrayList<Student>();
		studentList.add(new Student(update_id, "kishan", "panchal", "k@gmail.com"));
		// mock
		Organization mockorganization = new Organization(1, "alpha", "private", studentList);

		// mocking
		when(organizationService.updateOrganization(mockorganization, update_id)).thenReturn(mockorganization);
		Organization organization = organizationcontroller.updateOrganization(mockorganization, update_id);

		Mockito.verify(organizationService, times(1)).updateOrganization(organization, update_id);
		Assertions.assertThat(organization.getOrganizationid()).isNotEqualTo(update_id);
		assertEquals(mockorganization, organization);

	}

	

	@Test
	void getUserByIdNotFoundTest() {

		// request
		int organizationid = 1;
		// mock

		List<Student> studentList = new ArrayList<Student>();
		studentList.add(new Student(organizationid, "kishan", "panchal", "k@gmail.com"));
		// mock
		Organization mockorganization = new Organization(organizationid, "alpha", "private", studentList);

		System.out.print(mockorganization.getOrganizationid());

		// Mocking
		when(organizationService.findOrganizationById(organizationid)).thenReturn(mockorganization);

		// Test
		Organization organization = organizationcontroller.findOrganizationById(organizationid);
		// Assertion
		Mockito.verify(organizationService, times(1)).findOrganizationById(organizationid);
		assertEquals(organization, mockorganization);

	}

	@Test
	void test_deleteOrganization() {

		// request
		int deleteid = 1;

		// test

		doNothing().when(organizationService).deleteOrganization(deleteid);

		organizationcontroller.deleteOrganization(deleteid);

		Mockito.verify(organizationService, times(1)).deleteOrganization(deleteid);

	}

}
