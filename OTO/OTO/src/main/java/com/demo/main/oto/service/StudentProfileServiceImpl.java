package com.demo.main.oto.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.main.oto.exception.ResourceNotFoundException;
import com.demo.main.oto.model.StudentProfile;
import com.demo.main.oto.repository.StudentProfileRepository;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

	@Autowired
	private StudentProfileRepository studentProfileRepository;

	@Override
	public List<StudentProfile> getAllStudentProfile() {

		return studentProfileRepository.findAll();
	}

	@Override
	public StudentProfile addStudentProfile(StudentProfile studentProfile) {

		return studentProfileRepository.save(studentProfile);
	}

	@Override
	public StudentProfile getStudentProfileById(Integer id) {

		return getStudentByIdRequired(id);
	}

	private StudentProfile getStudentByIdRequired(Integer id) {

		return studentProfileRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("studentProfile id not found " + id));

	}

	@Override
	public StudentProfile updateStudentProfile(Integer id, StudentProfile studentProfile) {

		StudentProfile estudent = getStudentByIdRequired(id);
		estudent.setAddress(studentProfile.getAddress());
		estudent.setPhonenumber(studentProfile.getPhonenumber());
		return studentProfileRepository.save(estudent);

	}

	@Override
	public void deleteStudentProfileById(Integer id) {

		StudentProfile studentProfile=getStudentByIdRequired(id);
		studentProfileRepository.delete(studentProfile);

	}

}
