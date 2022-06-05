package com.demo.main.oto.service;

import java.util.List;

import com.demo.main.oto.model.StudentProfile;



public interface StudentProfileService {

	List<StudentProfile> getAllStudentProfile();

	StudentProfile addStudentProfile(StudentProfile studentProfile);

	StudentProfile getStudentProfileById(Integer id);

	StudentProfile updateStudentProfile(Integer id, StudentProfile studentProfile);

	void deleteStudentProfileById(Integer id);

}
