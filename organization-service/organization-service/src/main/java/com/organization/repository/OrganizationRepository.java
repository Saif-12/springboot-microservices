package com.organization.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.organization.entity.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

	Optional<Organization> findByOrganizationCode(String organizationCode);

}
