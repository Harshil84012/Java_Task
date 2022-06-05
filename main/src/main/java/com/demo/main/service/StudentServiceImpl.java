package com.demo.main.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import com.demo.main.dto.InputRequest;
import com.demo.main.exception.ResourceNotFoundException;
import com.demo.main.model.Student;
import com.demo.main.repository.StudentRepository;
import java.util.Calendar;
import java.util.List;
import javax.validation.Valid;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> getAllStudents() {

		return this.studentRepository.findAll();
	}

	@Override
	public String save(InputRequest<Student> request) {

		String currentUser = request.getLoggedInUser();
		request.setTimeZone(Calendar.getInstance().getTimeZone().getDisplayName());
		Student student = request.getStudent();
		student.setCreatedBy(currentUser);
		studentRepository.save(student);
		return "data added successfully";
	}

	@Override
	public ResponseEntity<Student> deleteStudent(@PathVariable("id") Integer id) {
		Student existingstudent = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("student not found with this id :" + id));
		this.studentRepository.delete(existingstudent);
		return ResponseEntity.ok().build();

	}

	@Override
	public Student getStudentById(Integer id) {

		return this.studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("student not found with id :" + id));
	}

	@Override
	public String updateStudent(Integer id, @Valid String fn, InputRequest<Student> request) {

		Student student = studentRepository.findById(id).get();
		if (student != null) {
			student.setFn(fn);
			student.setLastModifiedBy(request.getLoggedInUser());
			studentRepository.saveAndFlush(student);
		} 
		else 
		{
			throw new ResourceNotFoundException("student not found");
		}

		return "data updated";
	}

}
