package com.example.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.model.Employee;
import com.example.ems.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee updateEmployee(Long id, Employee employee) {

		Employee exemployee = findEmployeeById(id);
		exemployee.setEmailid(employee.getEmailid());
		exemployee.setFirstname(employee.getFirstname());
		exemployee.setLastname(employee.getLastname());
		return this.employeeRepository.save(exemployee);
	}

	@Override
	public Employee addEmployee(Employee employee) {

		return this.employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(Long id) {

		Employee employee = findEmployeeById(id);
		this.employeeRepository.delete(employee);
	}

	@Override
	public Employee findEmployeeById(Long id) {

		return this.employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("employee not found" + id));
	}

	@Override
	public List<Employee> findAllEmployees() {
		// TODO Auto-generated method stub
		return this.employeeRepository.findAll();
	}

}
