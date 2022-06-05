package com.example.ems.service;

import java.util.List;

import com.example.ems.model.Employee;

public interface EmployeeService {

	Employee updateEmployee(Long id, Employee employee);

	Employee addEmployee(Employee employee);

	void deleteEmployee(Long id);

	Employee findEmployeeById(Long id);

	List<Employee> findAllEmployees();


}
