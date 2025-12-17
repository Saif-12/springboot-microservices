package com.organization.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.organization.dto.OrganizationDto;
import com.organization.entity.Organization;
import com.organization.exception.ResourceNotFoundException;
import com.organization.repository.OrganizationRepository;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	private final OrganizationRepository organizationRepository;
	private final ModelMapper mapper;

	public OrganizationServiceImpl(OrganizationRepository organizationRepository, ModelMapper mapper) {
		this.organizationRepository = organizationRepository;
		this.mapper = mapper;
	}

	public OrganizationDto addOrganization(OrganizationDto organizationDto) {

		Organization organization = mapper.map(organizationDto, Organization.class);
		organizationRepository.save(organization);
		OrganizationDto orDto = mapper.map(organization, OrganizationDto.class);
		return orDto;
	}

	public OrganizationDto findOrganization(String organizationCode) {

		Organization organization = organizationRepository.findByOrganizationCode(organizationCode)
				.orElseThrow(() -> new ResourceNotFoundException("Organization", "organizationId", organizationCode));
		OrganizationDto organizationDto = mapper.map(organization, OrganizationDto.class);
		return organizationDto;
	}

}
