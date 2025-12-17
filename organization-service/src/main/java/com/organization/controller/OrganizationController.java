package com.organization.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organization.dto.OrganizationDto;
import com.organization.service.OrganizationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

	private final OrganizationService organizationService;

	public OrganizationController(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	// create organization
	@PostMapping
	public ResponseEntity<OrganizationDto> addOrganization(@RequestBody OrganizationDto organizationDto) {
		OrganizationDto organization = organizationService.addOrganization(organizationDto);
		return new ResponseEntity<>(organization, HttpStatus.CREATED);
	}

	// get organization by code
	@GetMapping("/{organizationCode}")
	public ResponseEntity<OrganizationDto> getOrganization(@PathVariable @Valid String organizationCode) {
		OrganizationDto organization = organizationService.findOrganization(organizationCode);
		return new ResponseEntity<>(organization, HttpStatus.OK);
	}

}
