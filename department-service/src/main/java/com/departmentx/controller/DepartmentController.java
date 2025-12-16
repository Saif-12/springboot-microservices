package com.departmentx.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.departmentx.dto.DepartmentDto;
import com.departmentx.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

	private final DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@PostMapping
	public ResponseEntity<DepartmentDto> addDepartment(@RequestBody DepartmentDto departmentDto) {
		DepartmentDto department = departmentService.addDepartment(departmentDto);
		return new ResponseEntity<>(department, HttpStatus.CREATED);
	}

	@GetMapping("/{departmentCode}")
	public ResponseEntity<DepartmentDto> getDepartment(@PathVariable String departmentCode) {
		DepartmentDto departmentByCode = departmentService.getDepartmentByCode(departmentCode);
		return new ResponseEntity<>(departmentByCode, HttpStatus.OK);
	}
}
