package com.demo.main.mto.service;

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

import com.demo.main.mto.exception.ResourceNotFoundException;
import com.demo.main.mto.model.Company;
import com.demo.main.mto.model.Employee;
import com.demo.main.mto.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

	@Mock
	EmployeeRepository employeeRepository;

	@InjectMocks
	EmployeeServiceImpl employeeServiceImpl;

	@Test

	void listAllEmployee() {

		Company company = new Company(1, "softvan", "private");
		List<Employee> employee = new ArrayList<>();
		employee.add(new Employee(1, "harshil", company));

		when(employeeRepository.findAll()).thenReturn(employee);
		List<Employee> eemployee = employeeServiceImpl.getAllEmployee();

		Mockito.verify(employeeRepository, times(1)).findAll();
		assertEquals(employee, eemployee);
	}

	@Test
	void getEmployeeById() {

		int studentid = 3;
		Company company = new Company(studentid, "innoventa", "private");
		Employee employee = new Employee(studentid, "harshil", company);

		when(employeeRepository.getById(studentid)).thenReturn(employee);
		Employee xemployee = employeeServiceImpl.getEmployeeById(studentid);

		verify(employeeRepository, times(1)).getById(studentid);

		assertEquals(employee, xemployee);

	}

	@Test

	void addEmployee() {
		int studentid = 1;
		Company company = new Company(studentid, "innoventa", "private");
		Employee mockemployee = new Employee(studentid, "harshil", company);

		when(employeeRepository.save(mockemployee)).thenReturn(mockemployee);

		Employee employee = employeeServiceImpl.addEmployee(mockemployee);

		Mockito.verify(employeeRepository).save(mockemployee);

		assertEquals(mockemployee, employee);

	}

	@Test

	void deleteEmployee() {
		int deleteid = 3;
		Company company = new Company(deleteid, "innoventa", "private");
		Employee mockemployee = new Employee(deleteid, "harshil", company);

		when(employeeRepository.findById(deleteid)).thenReturn(Optional.of(mockemployee));
		doNothing().when(employeeRepository).delete(mockemployee);

		employeeServiceImpl.deleteById(deleteid);

		verify(employeeRepository, times(1)).delete(mockemployee);

	}

	@Test
	void updateEmployeeByIdPassed() {

		// Request
		int mockEmployeeid = 2;

		// Mocks
		Company company = new Company(mockEmployeeid, "softvan", "private");
		Employee mockEmployee = new Employee(mockEmployeeid, "harshil", company);

		// Mocking
		when(employeeRepository.findById(mockEmployeeid)).thenReturn(Optional.empty());

		// test
		ResourceNotFoundException actualResponse = assertThrows(ResourceNotFoundException.class,
				() -> employeeServiceImpl.updateEmployee(mockEmployeeid, mockEmployee));

		// Assertion
		verify(employeeRepository, times(1)).findById(mockEmployeeid);
		assertNotNull(actualResponse);

	}

	@Test
	void updateEmployeeByIdNotFOund() {

		int updateid = 2;

		Company company = new Company(updateid, "innoventa", "private");
		Employee mockemployee = new Employee(updateid, "harshil", company);

		int mockcompanyId = 1;

		ResourceNotFoundException expeactedResponce = new ResourceNotFoundException("User Not Found With ID :");
		when(employeeRepository.findById(mockcompanyId)).thenThrow(expeactedResponce);
		ResourceNotFoundException actualResponce = assertThrows(ResourceNotFoundException.class,
				() -> employeeServiceImpl.updateEmployee(mockcompanyId, mockemployee));

		Mockito.verify(employeeRepository, times(1)).findById(mockcompanyId);
		assertEquals(expeactedResponce.getMessage(), actualResponce.getMessage());

	}
}
