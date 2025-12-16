package com.organization.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "organizations")
public class Organization {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String organizationName;
	@Column(nullable = false)
	private String organizationDescription;
	@Column(name = "organization_code", nullable = false,unique = true)
	private String organizationCode;
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getOrganizationDescription() {
		return organizationDescription;
	}
	public void setOrganizationDescription(String organizationDescription) {
		this.organizationDescription = organizationDescription;
	}
	public String getOrganizationCode() {
		return organizationCode;
	}
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public String toString() {
		return "Organization [id=" + id + ", organizationName=" + organizationName + ", organizationDescription="
				+ organizationDescription + ", organizationCode=" + organizationCode + ", createdDate=" + createdDate
				+ "]";
	}
	public Organization(Long id, String organizationName, String organizationDescription, String organizationCode,
			LocalDateTime createdDate) {
		this.id = id;
		this.organizationName = organizationName;
		this.organizationDescription = organizationDescription;
		this.organizationCode = organizationCode;
		this.createdDate = createdDate;
	}
	public Organization() {
	
	}
	
	
	
	

}
