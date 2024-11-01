package com.project.backend_api.service;

import java.util.List;
import java.util.Optional;

import com.project.backend_api.model.Employee;

public interface IEmployeeService {

	List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
    Employee saveEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
}
