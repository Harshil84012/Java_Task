package com.demo.main.oto.model;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "OneToOne_StudentProfile_table")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class StudentProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "address")
	private String address;
	@Column(name = "phonenumber")
	private String phonenumber;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public StudentProfile(int id, String address, String phonenumber) {
		super();
		this.id = id;
		this.address = address;
		this.phonenumber = phonenumber;
	}


}
