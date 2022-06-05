package com.demo.main.junit.junit5;

import static org.junit.Assert.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.demo.main.junit.controller.Controller;
import com.demo.main.junit.model.Student;
import com.demo.main.junit.service.StudentService;
import com.demo.main.test.Mathunit;

@SpringBootApplication
public class UnitTest {

	@InjectMocks
	Controller controller;

	@Mock
	StudentService studentservice;

	@Test
	public void test() {

		Mathunit obj = new Mathunit();
		int expected = 2;
		int actual = obj.add(1, 1);
		assertEquals(expected, actual, "this method is for sum of two object");

	}

	@Test
	public void testareaOfcircle() {
		Mathunit obj1 = new Mathunit();

		assertEquals(314.1592653589793, Mathunit.areaOfcircle(10), "should return right are of circle");
	}

	@Test
	public void testAddStudent() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Mockito.doReturn(101)
	       .when(studentservice)
	       .save(.any(Student.class)).thenReturn(true);
		Student student = new Student(1, "harshil", "panchal");
		ResponseEntity<Student> responseEntity = controller.addStudent(student);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(101);
		assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
		
		
		




       
       
       
       
       /*when(StudentService.save(any(Student.class)))*/


	}
	
	

}
