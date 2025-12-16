package com.departmentx.service;

import com.departmentx.dto.DepartmentDto;

public interface DepartmentService {
	
	DepartmentDto addDepartment(DepartmentDto departmentDto);
	
	DepartmentDto getDepartmentByCode(String departmentCode);

}
