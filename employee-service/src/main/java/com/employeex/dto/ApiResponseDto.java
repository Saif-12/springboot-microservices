package com.employeex.dto;

public class ApiResponseDto {

	private EmployeeDto employee;
	private DepartmentDto department;
	private OrganizationDto organization;

	public OrganizationDto getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationDto organization) {
		this.organization = organization;
	}

	public EmployeeDto getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDto employee) {
		this.employee = employee;
	}

	public DepartmentDto getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentDto department) {
		this.department = department;
	}

	public ApiResponseDto(EmployeeDto employee, DepartmentDto department, OrganizationDto organization) {
		this.employee = employee;
		this.department = department;
		this.organization = organization;
	}

}
