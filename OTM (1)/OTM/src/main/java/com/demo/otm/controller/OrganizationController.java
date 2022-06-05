package com.demo.otm.controller;

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

import com.demo.otm.dto.OrganizationDTO;
import com.demo.otm.model.Organization;
import com.demo.otm.service.OrganizationService;



@RestController
@RequestMapping("/organization")

public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;

	@GetMapping("/getAllOrganization")
	public List<Organization> getAllOrganization() {
		return organizationService.listAll();

	}

	@PostMapping("/addorganization")
	public Organization addOrganization(@RequestBody Organization organization) {
		return this.organizationService.saveOrganization(organization);

	}

	@GetMapping("/{id}")
	public Organization findOrganizationById(@PathVariable Integer id) {
		return organizationService.findOrganizationById(id);
	}

	@PutMapping("/{id}")
	public Organization updateOrganization(@RequestBody Organization organization, @PathVariable Integer id) {
		return organizationService.updateOrganization(organization, id);
	}

	@DeleteMapping("/{id}")
	public String deleteOrganization(@PathVariable Integer id) {
		organizationService.deleteOrganization(id);
		return "organization Deleted Successfully!";

	}

}
