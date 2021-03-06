package com.demo.otm.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.otm.exception.ResourceNotFoundException;
import com.demo.otm.model.Student;
import com.demo.otm.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student getStudenById(Integer id) {

		return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("student not found"));
	}

	@Override
	public Student updateStudent(Student student, Integer id) {

		Student stud = getStudenById(id);
		stud.setFirstname(student.getFirstname());
		stud.setLastname(student.getLastname());
		stud.setEmail(student.getEmail());
		return stud;
	}

	@Override
	public void deleteStudent(Integer id) {
		Student stud = getStudenById(id);
		studentRepository.delete(stud);
	}

	@Override
	public Student addstudent(Student student) {

		return studentRepository.save(student);

	}

	@Override
	public List<Student> getAllStudent() {
		// TODO Auto-generated method stub
		return this.studentRepository.findAll();
	}

}
