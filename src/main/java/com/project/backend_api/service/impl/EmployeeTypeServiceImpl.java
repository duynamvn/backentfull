package com.project.backend_api.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend_api.model.Employee;
import com.project.backend_api.model.EmployeeType;
import com.project.backend_api.repository.EmployeeRepository;
import com.project.backend_api.repository.EmployeeTypeRepository;
import com.project.backend_api.service.IEmployeeTypeService;

@Service
public class EmployeeTypeServiceImpl implements IEmployeeTypeService{

	@Autowired
	private EmployeeTypeRepository employeeTypeRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<EmployeeType> getAllEmployeeType() {
		// TODO Auto-generated method stub
		return employeeTypeRepository.findAll();
	}

	@Override
	public Optional<EmployeeType> getEmployeeTypeById(Long id) {
		// TODO Auto-generated method stub
		return employeeTypeRepository.findById(id);
	}

	@Override
	public EmployeeType createEmployeeType(EmployeeType employeeType) {
		// TODO Auto-generated method stub
		List<Employee> employees = Optional.ofNullable(employeeType.getEmployees()).orElse(List.of()).stream()
				.map(employee -> employeeRepository.findById(employee.getId()).orElse(employee))
				.collect(Collectors.toList());
		employeeType.setEmployees(employees);
		return employeeTypeRepository.save(employeeType);
	}

	@Override
	public EmployeeType updateEmployeeType(Long id, EmployeeType employeeType) {
		// TODO Auto-generated method stub
		EmployeeType existingEmployeeType = employeeTypeRepository.findById(id).orElseThrow();
		if (employeeType != null) {
			existingEmployeeType.setEmployeeTypeName(employeeType.getEmployeeTypeName());
			existingEmployeeType.setEmployees(employeeType.getEmployees());
			return employeeTypeRepository.save(existingEmployeeType);
		}
		return null;
	}

	@Override
	public void deleteEmployeeType(Long id) {
		// TODO Auto-generated method stub
		
	}

}
