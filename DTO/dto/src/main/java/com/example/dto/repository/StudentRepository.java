package com.example.dto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dto.model.Student;
import com.example.dto.rr.StudentRequest;
import com.example.dto.rr.StudentResponse;
@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{

	

}
