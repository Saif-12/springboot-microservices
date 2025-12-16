package com.employeex.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeex.dto.ApiResponseDto;
import com.employeex.dto.EmployeeDto;
import com.employeex.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {

		this.employeeService = employeeService;
	}

	@PostMapping
	public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
		EmployeeDto employee = employeeService.addEmployee(employeeDto);
		return new ResponseEntity<>(employee, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseDto> findEmployeeById(@PathVariable Long id) {
		ApiResponseDto employeeById = employeeService.findEmployeeById(id);
		return new ResponseEntity<>(employeeById, HttpStatus.OK);
	}

}
