package com.employeex.service;

import java.time.LocalDateTime;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.employeex.dto.ApiResponseDto;
import com.employeex.dto.DepartmentDto;
import com.employeex.dto.EmployeeDto;
import com.employeex.dto.OrganizationDto;
import com.employeex.entity.Employee;
import com.employeex.exception.ResourceNotFoundException;
import com.employeex.repository.EmployeeRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	private final EmployeeRepository employeeRepository;

	private final ModelMapper modelMapper;

	// private final RestTemplate restTemplate;
	private final WebClient.Builder webClientBuilder;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper,
			WebClient.Builder webClientBuilder) {
		this.employeeRepository = employeeRepository;
		this.modelMapper = modelMapper;
		// this.restTemplate = restTemplate;
		this.webClientBuilder = webClientBuilder;
		System.out.println(">>> WebClientBuilder class = " + webClientBuilder.getClass());

	}

	public EmployeeDto addEmployee(EmployeeDto employeeDto) {

		Employee employee = modelMapper.map(employeeDto, Employee.class);

		Employee savedEmployee = employeeRepository.save(employee);

		EmployeeDto eDto = modelMapper.map(savedEmployee, EmployeeDto.class);

		return eDto;
	}

	// @CircuitBreaker(name = "${spring.application.name}", fallbackMethod =
	// "getDefaultDepartment")
	@Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
	public ApiResponseDto findEmployeeById(Long id) {

		LOGGER.info("inside findByEmployeeId()");

		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

//		ResponseEntity<DepartmentDto> resdepartmentDto = restTemplate.getForEntity(
//				"http://localhost:8080/api/departments/" + employee.getDepartmentCode(), DepartmentDto.class);
//
//		DepartmentDto departmentDto = resdepartmentDto.getBody();

		OrganizationDto organizationDto = webClientBuilder.build().get()
				.uri("http://ORGANIZATION-SERVICE/api/organizations/" + employee.getOrganizationCode()).retrieve()
				.bodyToMono(OrganizationDto.class).block();

		DepartmentDto departmentDto = webClientBuilder.build().get()
				.uri("http://DEPARTMENT-SERVICE/api/departments/" + employee.getDepartmentCode()).retrieve()
				.bodyToMono(DepartmentDto.class).block();

		EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

		ApiResponseDto apiResponseDto = new ApiResponseDto(employeeDto, departmentDto, organizationDto);

		return apiResponseDto;
	}

	public ApiResponseDto getDefaultDepartment(Long id, Exception e) {

		LOGGER.info("inside getDefaultDepartment()");

		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

		OrganizationDto organizationDto = new OrganizationDto();
		organizationDto.setId(1L);
		organizationDto.setOrganizationName("XY");
		organizationDto.setOrganizationDescription("provides policy service");
		organizationDto.setOrganizationCode("xxxx");
		// organizationDto.setCreatedDate(new LocalDateTime());
		DepartmentDto departmentDto = new DepartmentDto(1L, "Management", "MN001", "Management Department");

		EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

		ApiResponseDto apiResponseDto = new ApiResponseDto(employeeDto, departmentDto, organizationDto);

		return apiResponseDto;
	}

}
