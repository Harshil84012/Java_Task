package com.demo.main.security.model;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomStudentDetail implements UserDetails {

	private static final long serialVersionUID = 1L;
	@Autowired
	private Student student;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		HashSet<SimpleGrantedAuthority> set = new HashSet<>();
		set.add(new SimpleGrantedAuthority(this.student.getUsername()));
		return set;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.student.getPassword();
	}

	public CustomStudentDetail(Student student) {
		super();
		this.student = student;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.student.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
