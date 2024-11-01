package com.project.backend_api.controller;

import java.util.List;

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

import com.project.backend_api.model.EmployeeType;
import com.project.backend_api.service.IEmployeeTypeService;

@RestController
@RequestMapping("/api/employee-type")
public class EmployeeTypeController {

	@Autowired
	private IEmployeeTypeService iEmployeeTypeService;
	
	@GetMapping
	public ResponseEntity<List<EmployeeType>> getAllEmployeeType(){
		List<EmployeeType> employeeTypes = iEmployeeTypeService.getAllEmployeeType();
		return ResponseEntity.ok(employeeTypes);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeType> getEmployeeTypeById(@PathVariable Long id){
		EmployeeType employeeType = iEmployeeTypeService.getEmployeeTypeById(id).orElseThrow();
		return ResponseEntity.ok(employeeType);
	}
	
	@PostMapping
	public ResponseEntity<EmployeeType> createEmployeeType(@RequestBody EmployeeType employeeType){
		EmployeeType createEmployeeType = iEmployeeTypeService.createEmployeeType(employeeType);
		return ResponseEntity.ok(createEmployeeType);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeType> updateEmployeeType(@PathVariable Long id, @RequestBody EmployeeType employeeType){
		EmployeeType updateEmployeeType = iEmployeeTypeService.updateEmployeeType(id, employeeType);
		return ResponseEntity.ok(updateEmployeeType);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<EmployeeType> deleteEmployeeType(@PathVariable Long id){
		iEmployeeTypeService.deleteEmployeeType(id);
		return ResponseEntity.noContent().build();
	}
}
