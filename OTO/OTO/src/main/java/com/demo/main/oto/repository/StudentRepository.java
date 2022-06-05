package com.demo.main.oto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.main.oto.model.Student;



public interface StudentRepository extends JpaRepository<Student, Integer> {

}
