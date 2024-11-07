package com.project.backend_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend_api.model.Employee;
import com.project.backend_api.repository.EmployeeRepository;
import com.project.backend_api.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService{

	@Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        Optional<Employee> employeeWithSameEmail = employeeRepository.findByEmail(employee.getEmail());
        if (employeeWithSameEmail.isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        Optional<Employee> employeeWithSameNationalId = employeeRepository.findByNationalID(employee.getNationalID());
        if (employeeWithSameNationalId.isPresent()) {
            throw new IllegalArgumentException("National ID already exists");
        }
        Optional<Employee> employeeWithSamePhoneNumber = employeeRepository.findByPhoneNumber(employee.getPhoneNumber());
        if (employeeWithSamePhoneNumber.isPresent()) {
            throw new IllegalArgumentException("Phone number already exists");
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(null);

        Optional<Employee> employeeWithSameEmail = employeeRepository.findByEmail(employee.getEmail());
        if (employeeWithSameEmail.isPresent() && !employeeWithSameEmail.get().getId().equals(id)) {
            throw new IllegalArgumentException("Email already exists");
        }

        Optional<Employee> employeeWithSameNationalId = employeeRepository.findByNationalID(employee.getNationalID());
        if (employeeWithSameNationalId.isPresent() && !employeeWithSameNationalId.get().getId().equals(id)) {
            throw new IllegalArgumentException("National ID already exists");
        }
        Optional<Employee> employeeWithSamePhoneNumber = employeeRepository.findByPhoneNumber(employee.getPhoneNumber());
        if (employeeWithSamePhoneNumber.isPresent() && !employeeWithSamePhoneNumber.get().getId().equals(id)) {
            throw new IllegalArgumentException("Phone Number already exists");
        }

        existingEmployee.setFullName(employee.getFullName());
        existingEmployee.setDateOfBirth(employee.getDateOfBirth());
        existingEmployee.setGender(employee.getGender());
        existingEmployee.setNationalID(employee.getNationalID());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhoneNumber(employee.getPhoneNumber());
        existingEmployee.setActivate(employee.getActivate());
        existingEmployee.setAddress(employee.getAddress());
        existingEmployee.setImageName(employee.getImageName());

        existingEmployee.setDegree(employee.getDegree());
        existingEmployee.setSpecialization(employee.getSpecialization());

        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

}
