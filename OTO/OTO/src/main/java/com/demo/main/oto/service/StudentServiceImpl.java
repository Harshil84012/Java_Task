package com.demo.main.oto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.main.oto.exception.ResourceNotFoundException;
import com.demo.main.oto.model.Student;
import com.demo.main.oto.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> getAllStudent() {

		return studentRepository.findAll();
	}

	@Override
	public Student addStudent(Student student) {

		return studentRepository.save(student);
	}

	@Override
	public Student getStudentById(Integer id) {

		return getStudentByIdRequired(id);
	}

	@Override
	public Student updateStudent(Integer id, Student student) {

		Student estudent = getStudentByIdRequired(id);
		estudent.setFirstname(student.getFirstname());
		estudent.setLastname(student.getLastname());
		estudent.setStudentProfile(student.getStudentProfile());
		return studentRepository.save(estudent);
	}

	@Override
	public void deleteStudentById(Integer id) {

		Student student=getStudentByIdRequired(id);
		studentRepository.delete(student);
	}

	private Student getStudentByIdRequired(Integer id) {
		return studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("student id not found " + id));

	}

}
