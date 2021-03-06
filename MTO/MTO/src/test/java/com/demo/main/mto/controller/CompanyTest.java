package com.demo.main.mto	.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;



import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import com.demo.main.mto.exception.ResourceNotFoundException;
import com.demo.main.mto.model.Company;
import com.demo.main.mto.service.CompanyService;

@ExtendWith(MockitoExtension.class)
class CompanyTest {

	@Mock
	CompanyService companyService;

	@InjectMocks
	CompanyController companyController;

	@Test
	 void getAllCompany() {
		List<Company> company = new ArrayList<Company>();
		company.add(new Company(1, "softvan", "private"));

		when(companyService.getAllCompany()).thenReturn(company);

		List<Company> mockcompany = companyService.getAllCompany();

		Mockito.verify(companyService, times(1)).getAllCompany();

		assertEquals(mockcompany, company);
	}

	@Test
	 void addCompany() {

		Company company = new Company(1, "alpha", "private");
		when(companyService.addCompany(company)).thenReturn(company);
		System.out.println(company.getCname());

		Company mockcompany = companyController.addCompany(company);

		Mockito.verify(companyService, times(1)).addCompany(mockcompany);

		System.out.println(mockcompany.getCname());

		assertEquals(mockcompany, company);
	}

	@Test
	 void updateCompany() {

		int updateid = 3;
		Company company = new Company(1, "alpha", "private");

		when(companyService.updateCompany(updateid, company)).thenReturn(company);

		Company mockcompany = companyController.updateCompany(updateid, company);

		company.setCname("git");
		company.setCtype("gov");

		Mockito.verify(companyService).updateCompany(updateid, company);
		Assertions.assertThat(company.getId()).isNotEqualTo(updateid);

		assertEquals(mockcompany, company);

	}

	@Test
	 void getCompanyByIdNotFound() {

		Company company = new Company(1, "alpha", "private");

		Integer mockcompanyId = 2;

		ResourceNotFoundException expeactedResponce = new ResourceNotFoundException("id not found");

		when(companyService.getCompanyById(mockcompanyId)).thenThrow(expeactedResponce);

		ResourceNotFoundException actualResponce = assertThrows(ResourceNotFoundException.class,
				() -> companyController.getCompanyById(mockcompanyId));

		Mockito.verify(companyService, times(1)).getCompanyById(mockcompanyId);

		Assertions.assertThat(expeactedResponce.getMessage()).isEqualTo(actualResponce.getMessage());
	}

	@Test
	 void deleteCompany() {

		int deleteid = 3;

		doNothing().when(companyService).deleteById(deleteid);

		companyController.deleteCompany(deleteid);

		Mockito.verify(companyService, times(1)).deleteById(deleteid);

	}
}
