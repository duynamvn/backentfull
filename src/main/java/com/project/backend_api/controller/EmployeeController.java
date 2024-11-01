package com.project.backend_api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = iEmployeeService.getEmployeeById(id)
                                            .orElseThrow(() -> new RuntimeException("Employee not found"));
        return ResponseEntity.ok(employee);
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
}
