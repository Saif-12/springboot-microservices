package com.departmentx.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.departmentx.dto.DepartmentDto;
import com.departmentx.entity.Department;
import com.departmentx.exception.ResourceNotFoundException;
import com.departmentx.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepository departmentRepository;

	private final ModelMapper modelMapper;

	public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper) {

		this.departmentRepository = departmentRepository;
		this.modelMapper = modelMapper;
	}

	public DepartmentDto addDepartment(DepartmentDto departmentDto) {

		Department department = modelMapper.map(departmentDto, Department.class);

		Department savedDepartment = departmentRepository.save(department);

		DepartmentDto deDto = modelMapper.map(savedDepartment, DepartmentDto.class);

		return deDto;

	}

	@Override
	public DepartmentDto getDepartmentByCode(String departmentCode) {

		Department department = departmentRepository.findByDepartmentCode(departmentCode)
				.orElseThrow(() -> new ResourceNotFoundException("Department", "id", departmentCode));

		DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);
		return departmentDto;
	}

}
