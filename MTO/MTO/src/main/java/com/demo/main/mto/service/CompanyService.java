package com.demo.main.mto.service;

import java.util.List;


import com.demo.main.mto.model.Company;

public interface CompanyService {

	void deleteById(Integer id);

	Company updateCompany(Integer id, Company company);

	Company getCompanyById(Integer id);

	Company addCompany(Company company);

	List<Company> getAllCompany();

	

}
