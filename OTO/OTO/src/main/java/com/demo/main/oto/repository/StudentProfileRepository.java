package com.demo.main.oto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.main.oto.model.StudentProfile;



public interface StudentProfileRepository extends JpaRepository<StudentProfile, Integer> {

}
