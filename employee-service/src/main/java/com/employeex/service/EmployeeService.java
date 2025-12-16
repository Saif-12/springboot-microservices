package com.employeex.service;

import com.employeex.dto.ApiResponseDto;
import com.employeex.dto.EmployeeDto;

public interface EmployeeService {

	EmployeeDto addEmployee(EmployeeDto employeeDto);

	ApiResponseDto findEmployeeById(Long id);

}
