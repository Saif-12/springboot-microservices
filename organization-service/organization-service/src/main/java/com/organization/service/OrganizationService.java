package com.organization.service;

import com.organization.dto.OrganizationDto;

public interface OrganizationService {
	
	OrganizationDto addOrganization(OrganizationDto organizationDto);
	
	OrganizationDto findOrganization(String  organizationCode);

}
