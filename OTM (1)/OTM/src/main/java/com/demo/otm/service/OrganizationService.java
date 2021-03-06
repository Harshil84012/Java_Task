package com.demo.otm.service;

import java.util.List;

import com.demo.otm.dto.OrganizationDTO;
import com.demo.otm.model.Organization;

public interface OrganizationService {

	List<Organization> listAll();

	Organization saveOrganization(Organization organization);

	Organization findOrganizationById(Integer id);

	Organization updateOrganization(Organization organization, Integer id);

	void deleteOrganization(Integer id);


	

	

	




	

}
