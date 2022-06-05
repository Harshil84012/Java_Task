package com.demo.main.oto.model;

import javax.persistence.CascadeType;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@Entity
@Table(name = "OneToOne_Student_Table")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "first_name")
	private String firstname;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public StudentProfile getStudentProfile() {
		return studentProfile;
	}

	public void setStudentProfile(StudentProfile studentProfile) {
		this.studentProfile = studentProfile;
	}

	@Column(name = "last_name")
	private String lastname;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "f_key")
	private StudentProfile studentProfile;
	public Student(int id, String firstname, String lastname, StudentProfile studentProfile) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.studentProfile = studentProfile;
	}
	
	
	

}
