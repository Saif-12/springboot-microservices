package com.departmentx.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.departmentx.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
	//departmentCode
	Optional<Department> findByDepartmentCode(String departmentCode);

}
