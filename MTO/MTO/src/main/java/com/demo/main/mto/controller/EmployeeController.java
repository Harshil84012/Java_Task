package com.demo.main.mto.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.main.mto.model.Employee;
import com.demo.main.mto.service.EmployeeService;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/getall")
	public List<Employee> getAllEmployee() {
		return employeeService.getAllEmployee();
	}

	@PostMapping("/addemployee")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}

	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable Integer id) {
		return employeeService.getEmployeeById(id);
	}

	@PutMapping("/{id}")
	public Employee updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
		return employeeService.updateEmployee(id, employee);
	}

	@DeleteMapping("/{id}")
	public String deleteEmployee(@PathVariable Integer id) {
		employeeService.deleteById(id);
		return "data deleted Successfully!";
	}

}
