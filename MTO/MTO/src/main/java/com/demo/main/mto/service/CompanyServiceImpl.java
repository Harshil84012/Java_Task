package com.demo.main.mto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.main.mto.exception.ResourceNotFoundException;
import com.demo.main.mto.model.Company;
import com.demo.main.mto.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public void deleteById(Integer id) {
		
		Company company=getCompanyById(id);

		companyRepository.delete(company);

	}

	@Override
	public Company updateCompany(Integer id, Company company) {

		Company ecompany = getCompanyByIdRequired(id);
		ecompany.setCname(company.getCname());
		ecompany.setCtype(company.getCtype());
		return companyRepository.save(ecompany);
	}

	@Override
	public Company getCompanyById(Integer id) {

		return getCompanyByIdRequired(id);
	}

	@Override
	public Company addCompany(Company company) {

		return companyRepository.save(company);
	}

	@Override
	public List<Company> getAllCompany() {

		return companyRepository.findAll();
	}

	private Company getCompanyByIdRequired(Integer id) {

		return companyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("company  id not found " + id));

	}

}
