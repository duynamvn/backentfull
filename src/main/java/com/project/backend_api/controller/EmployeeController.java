package com.project.backend_api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend_api.dto.EmployeeDTO;
import com.project.backend_api.mapper.EmployeeMapper;
import com.project.backend_api.model.Employee;
import com.project.backend_api.service.IEmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private IEmployeeService iEmployeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>>  getAllEmployees() {
    	List<Employee> employees = iEmployeeService.getAllEmployees();
    	List<EmployeeDTO> employeeDTO = employees.stream()
    			.map(EmployeeMapper::toDto)
    			.collect(Collectors.toList());
        return ResponseEntity.ok(employeeDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        Employee employee = iEmployeeService.getEmployeeById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        EmployeeDTO employeeDTO = EmployeeMapper.toDto(employee);
        return ResponseEntity.ok(employeeDTO);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return iEmployeeService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return iEmployeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        iEmployeeService.deleteEmployee(id);
    }
    
    @GetMapping("/employee-dto/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeDtoById(@PathVariable Long id){
    	Optional<Employee> employee = iEmployeeService.getEmployeeById(id);
    	return employee.map(EmployeeMapper::toDto).map(ResponseEntity::ok)
    			.orElseGet(() -> ResponseEntity.noContent().build());
    }

    @Valid
    @PostMapping("/addEmployee")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
        if (iEmployeeService.existsByEmail(employee.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        if (iEmployeeService.existsByNationalID(employee.getNationalID())) {
            return ResponseEntity.badRequest().body("National ID already exists");
        }
        iEmployeeService.saveEmployee(employee);
        return ResponseEntity.ok("Employee added successfully");
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Employee> deactivateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        try {
            iEmployeeService.updateIsActiveStatus(id, employee.getIsActive());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("active")
    public ResponseEntity<?> getAllActiveEmployees() {
        List<Employee> employees = iEmployeeService.getAllEmployees();
        if (employees == null || employees.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }
        List<Employee> activeEmployees = employees.stream()
                .filter(employee -> employee.getIsActive() != null && employee.getIsActive())
                .collect(Collectors.toList());
        if (activeEmployees.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }
        List<EmployeeDTO> employeeDTOs = activeEmployees.stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(employeeDTOs);
    }
}
