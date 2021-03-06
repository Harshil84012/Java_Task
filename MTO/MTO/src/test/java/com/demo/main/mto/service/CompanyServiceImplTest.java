package com.demo.main.mto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.main.mto.exception.ResourceNotFoundException;
import com.demo.main.mto.model.Company;
import com.demo.main.mto.repository.CompanyRepository;

@ExtendWith(MockitoExtension.class)
class CompanyServiceImplTest {

	@Mock
	CompanyRepository companyRepository;

	@InjectMocks
	CompanyServiceImpl companyServiceImpl;

	Company company;

	@Test
	void getAllCompany() {
		List<Company> company = new ArrayList<Company>();
		company.add(new Company(1, "softvan", "private"));

		when(companyRepository.findAll()).thenReturn(company);

		List<Company> ecompany = companyServiceImpl.getAllCompany();
		Mockito.verify(companyRepository, times(1)).findAll();

		assertEquals(company, ecompany);

	}

	@Test
	void getCompanyById() {

		int studentid = 3;

		Company company = new Company(studentid, "softvan", "private");

		when(companyRepository.findById(studentid)).thenReturn(Optional.of(company));

		Company mockcompany = companyServiceImpl.getCompanyById(studentid);

		verify(companyRepository, times(1)).findById(studentid);

		assertEquals(company, mockcompany);

	}

	@Test
	void addCompany() {
		Company company = new Company(1, "softvan", "private");
		when(companyRepository.save(company)).thenReturn(company);
		Company mockcompany = companyServiceImpl.addCompany(company);
		Mockito.verify(companyRepository).save(mockcompany);
		assertEquals(company, mockcompany);

	}

	@Test
	void deleteCompany() {

		int deleteid = 3;
		Company company = new Company(1, "softvan", "private");

		when(companyRepository.findById(deleteid)).thenReturn(Optional.of(company));
		doNothing().when(companyRepository).delete(company);

		companyServiceImpl.deleteById(deleteid);
		verify(companyRepository, times(1)).delete(company);

	}

	@Test
	void updateCompanyByIdPassed() {
		Integer companyid = 2;
		Company company = new Company(1, "softvan", "private");

		Company dbcompany = new Company(1, "softvan", "private");
		when(companyRepository.findById(companyid)).thenReturn(Optional.of(dbcompany));
		when(companyRepository.save(dbcompany)).then(i -> i.getArgument(0));

		Company actualcompany = (Company) companyServiceImpl.updateCompany(companyid, dbcompany);
		Mockito.verify(companyRepository, times(1)).findById(companyid);
		Mockito.verify(companyRepository, times(1)).save(dbcompany);
		assertEquals(dbcompany, actualcompany);

	}

	@Test
	void updateCompanyByIdNotFOund() {
		Company company = new Company(1, "softvan", "private");

		Integer mockcompanyId = 1;

		ResourceNotFoundException expeactedResponce = new ResourceNotFoundException("User Not Found With ID :");
		when(companyRepository.findById(mockcompanyId)).thenThrow(expeactedResponce);
		ResourceNotFoundException actualResponce = assertThrows(ResourceNotFoundException.class,
				() -> companyServiceImpl.getCompanyById(mockcompanyId));

		Mockito.verify(companyRepository, times(1)).findById(mockcompanyId);
		assertEquals(expeactedResponce.getMessage(), actualResponce.getMessage());

	}

	@Test
	void deleteCompanyByIdnotFound() {
		
		
		int deleteid = 3;
		Company company = new Company(1, "softvan", "private");

		when(companyRepository.findById(deleteid)).thenReturn(Optional.empty());

		ResourceNotFoundException actualResponce = assertThrows(ResourceNotFoundException.class,
				() -> companyServiceImpl.deleteById(deleteid));
	

		Mockito.verify(companyRepository, times(1)).findById(deleteid);
		assertNotNull(actualResponce);

	}

}
