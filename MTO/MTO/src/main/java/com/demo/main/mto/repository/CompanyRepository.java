package com.demo.main.mto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.main.mto.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
