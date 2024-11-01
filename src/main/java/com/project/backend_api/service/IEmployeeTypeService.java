package com.project.backend_api.service;

import java.util.List;
import java.util.Optional;

import com.project.backend_api.model.EmployeeType;

public interface IEmployeeTypeService {

	List<EmployeeType> getAllEmployeeType();
	Optional<EmployeeType> getEmployeeTypeById(Long id);
	EmployeeType createEmployeeType(EmployeeType employeeType);
	EmployeeType updateEmployeeType(Long id, EmployeeType employeeType);
	void deleteEmployeeType(Long id);
}
