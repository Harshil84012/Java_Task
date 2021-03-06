package com.demo.otm.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.otm.exception.ResourceNotFoundException;
import com.demo.otm.model.Organization;

import com.demo.otm.repository.OrganizationRepository;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationRepository organizationRepository;
	

	@Override
	public void deleteOrganization(Integer id) {
		Organization organization = findOrganizationById(id);
		organizationRepository.delete(organization);

	}

	public Organization findOrganizationById(Integer id) {
		return organizationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("organization not found by id"));
	}

	@Override
	public Organization saveOrganization(Organization organization) {

		return organizationRepository.save(organization);
	}

	@Override
	public Organization updateOrganization(Organization organization, Integer id) {

		Organization eorganization = findOrganizationById(id);
		eorganization.setOrganizationname(organization.getOrganizationname());
		eorganization.setOrganizationType(organization.getOrganizationType());
		return organizationRepository.save(eorganization);
	}

	@Override
	public List<Organization> listAll() {
		return organizationRepository.findAll();
		
	}

}
