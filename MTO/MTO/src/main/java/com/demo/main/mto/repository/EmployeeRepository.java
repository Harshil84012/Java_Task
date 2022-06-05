package com.demo.main.mto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.main.mto.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
