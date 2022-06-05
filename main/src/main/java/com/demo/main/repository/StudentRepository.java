package com.demo.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.demo.main.model.Student;



public interface StudentRepository extends JpaRepository<Student, Integer> {

}
