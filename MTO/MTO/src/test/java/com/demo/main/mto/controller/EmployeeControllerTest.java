package com.demo.main.mto.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import com.demo.main.mto.model.Company;
import com.demo.main.mto.model.Employee;
import com.demo.main.mto.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

	@Mock
	EmployeeService employeeService;

	@InjectMocks
	EmployeeController employeeController;

	@Test
	void getAllEmployee() {

		Company company = new Company(1, "alpha", "private");
		List<Employee> mockemployee = new ArrayList<Employee>();
		mockemployee.add(new Employee(0, "harshil", company));

		when(employeeService.getAllEmployee()).thenReturn(mockemployee);

		List<Employee> employee = employeeController.getAllEmployee();

		Mockito.verify(employeeService, times(1)).getAllEmployee();
		assertEquals(mockemployee, employee);

	}

	@Test
	void addEmployee() {

		int employeeid = 3;
		Company company = new Company(employeeid, "alpha", "private");
		Employee mockemployee = new Employee(employeeid, "harshil", company);

		when(employeeService.addEmployee(mockemployee)).thenReturn(mockemployee);

		Employee employee = employeeController.addEmployee(mockemployee);

		Mockito.verify(employeeService, times(1)).addEmployee(employee);
		assertEquals(mockemployee, employee);
	}

	@Test
	void updateEmployee() {
		int updateid = 3;
		Company company = new Company(updateid, "alpha", "private");
		Employee mockemployee = new Employee(updateid, "harshil", company);

		when(employeeService.updateEmployee(updateid, mockemployee)).thenReturn(mockemployee);
		Employee employee = employeeController.updateEmployee(updateid, mockemployee);
		Mockito.verify(employeeService, times(1)).updateEmployee(updateid, employee);

		Assertions.assertThat(company.getId()).isEqualTo(updateid);

		assertEquals(mockemployee, employee);

	}

	@Test
	void getEmployeeId() {
		int employeeid = 3;
		Company company = new Company(employeeid, "alpha", "private");
		Employee mockemployee = new Employee(employeeid, "harshil", company);

		when(employeeService.getEmployeeById(employeeid)).thenReturn(mockemployee);
		Employee employee = employeeController.getEmployeeById(employeeid);

		Mockito.verify(employeeService, times(1)).getEmployeeById(employeeid);

		assertEquals(mockemployee, employee);

	}

	@Test
	void getEmployeeByIdNotFoundTest() {

		// request
		int employeeid = 1;
		// mock

		Company company = new Company(employeeid, "alpha", "private");
		Employee mockemployee = new Employee(employeeid, "harshil", company);

		// Mocking
		when(employeeService.getEmployeeById(employeeid)).thenReturn(mockemployee);

		// Test
		Employee employee = employeeController.getEmployeeById(employeeid);

		// Assertion
		Mockito.verify(employeeService, times(1)).getEmployeeById(employeeid);
		assertEquals(mockemployee, employee);

	}

	@Test
	void deleteEmployee() {

		int deleteid = 3;

		doNothing().when(employeeService).deleteById(deleteid);

		employeeController.deleteEmployee(deleteid);

		Mockito.verify(employeeService, times(1)).deleteById(deleteid);
	}

}
