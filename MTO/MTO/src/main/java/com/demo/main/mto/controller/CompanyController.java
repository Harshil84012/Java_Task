package com.demo.main.mto.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.main.mto.model.Company;
import com.demo.main.mto.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@GetMapping("/getall")
	public List<Company> getAllCompany() {
		return companyService.getAllCompany();
	}

	@PostMapping("/addcompany")
	public Company addCompany(@RequestBody Company company) {
		return companyService.addCompany(company);
	}

	@GetMapping("/{id}")

	public Company getCompanyById(@PathVariable Integer id) {
		return companyService.getCompanyById(id);
	}

	@PutMapping("/{id}")
	public Company updateCompany(@PathVariable Integer id, @RequestBody Company company) {
		return companyService.updateCompany(id, company);
	}

	@DeleteMapping("/{id}")
	public String deleteCompany(@PathVariable Integer id) {
		this.companyService.deleteById(id);
		return "company deleted";

	}

}
