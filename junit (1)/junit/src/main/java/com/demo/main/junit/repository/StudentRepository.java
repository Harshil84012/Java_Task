package com.demo.main.junit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.main.junit.model.Student;

public interface StudentRepository extends JpaRepository<Student,Integer>{
	
}
