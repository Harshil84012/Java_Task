package com.demo.main.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.main.security.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	Student getByUsername(String username);



}
