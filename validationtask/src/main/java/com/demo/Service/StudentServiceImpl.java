package com.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.model.Student;
import com.demo.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	@Override
	public String save(Student student) {
		// TODO Auto-generated method stub
		this.studentRepository.save(student);
		return "data added";
	}

	@Override
	public Student getStudentById(Integer id) {
		// TODO Auto-generated method stub
		return studentRepository.findOne(id);
	}

	@Override
	public String updateStudent(Integer id, Student student) {
		// TODO Auto-generated method stub
		Student estudent = getStudentById(id);
		estudent.setEmailid(student.getEmailid());
		estudent.setFn(student.getFn());
		estudent.setLn(student.getLn());
		this.studentRepository.save(estudent);
		return "data updated successfully";
	}

	@Override
	public String deleteStudent(Integer id) {
		// TODO Auto-generated method stub
		this.studentRepository.delete(id);
		return "data deleted Successfully";
	}

}
