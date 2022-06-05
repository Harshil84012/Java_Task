package com.demo.main.mto.service;

import java.util.List;


import com.demo.main.mto.model.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployee();

	Employee addEmployee(Employee employee);

	Employee getEmployeeById(Integer id);

	Employee updateEmployee(Integer id, Employee employee);

	void deleteById(Integer id);


}
