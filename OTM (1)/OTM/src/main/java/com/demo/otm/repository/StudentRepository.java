package com.demo.otm.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.demo.otm.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
