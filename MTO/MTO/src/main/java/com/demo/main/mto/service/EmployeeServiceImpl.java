package com.demo.main.mto.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.main.mto.exception.ResourceNotFoundException;
import com.demo.main.mto.model.Employee;
import com.demo.main.mto.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployee() {

		return employeeRepository.findAll();
	}

	@Override
	public Employee addEmployee(Employee employee) {

		return employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployeeById(Integer id) {

		return employeeRepository.getById(id);
	}

	@Override
	public Employee updateEmployee(Integer id, Employee employee) {

		Employee eemployee = getEmployeeByIdRequired(id);
		eemployee.setName(employee.getName());
		eemployee.setCompany(employee.getCompany());
		return employeeRepository.save(eemployee);
	}

	@Override
	public void deleteById(Integer id) {

		Employee employee = getEmployeeByIdRequired(id);

		employeeRepository.delete(employee);

	}

	private Employee getEmployeeByIdRequired(Integer id) {

		return employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("employee  id not found " + id));

	}

}
