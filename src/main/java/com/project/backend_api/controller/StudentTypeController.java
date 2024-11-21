package com.project.backend_api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.project.backend_api.dto.StudentTypeDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
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

import com.project.backend_api.model.StudentType;
import com.project.backend_api.service.IStudentTypeService;

@RestController
@RequestMapping("/api/student-type")
public class StudentTypeController {

	@Autowired
	private IStudentTypeService iStudentTypeService;
	
	@GetMapping
	public List<StudentType> getAllStudentType(){
		return iStudentTypeService.getAllStudentType();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentType> getStudentTypeById(@PathVariable Long id) {
		StudentType studentType = iStudentTypeService.getStudentTypeById(id);
		return ResponseEntity.ok(studentType);
	}
	
	@PostMapping
	public ResponseEntity<StudentType> createStudentType(@RequestBody StudentType studentType){
		StudentType createStudentType = iStudentTypeService.createStudentType(studentType);
		return ResponseEntity.ok(createStudentType);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<StudentType> updateStudentType(@PathVariable Long id, @RequestBody StudentType studentType){
		StudentType updateStudentType = iStudentTypeService.updateStudentType(id, studentType);
		updateStudentType.setStudentTypeName(studentType.getStudentTypeName());
		return ResponseEntity.ok(updateStudentType);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<StudentType> deleteStudentType(@PathVariable Long id){
		iStudentTypeService.deleteStudentType(id);
		return ResponseEntity.noContent().build();
	}

	@Valid
	@PostMapping("/addStudentType")
	public ResponseEntity<?> addStudentType(@RequestBody StudentType studentType, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			List<String> errorMessages = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());
			return ResponseEntity.badRequest().body(errorMessages);
		}
		iStudentTypeService.createStudentType(studentType);
		return ResponseEntity.ok("Student Type Added Successfully");
	}
	@PutMapping("{id}/deactivate")
	public ResponseEntity<StudentType> deactivateStudentType(@PathVariable("id") Long id, @RequestBody StudentType studentType){
		try {
			iStudentTypeService.updateIsActiveStatus(id, studentType.getIsActive());
			return ResponseEntity.ok().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	@GetMapping("/active")
	public ResponseEntity<?> getAllActiveStudentType(){
		List<StudentType> allStudentType = iStudentTypeService.getAllStudentType();
		if (allStudentType == null || allStudentType.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		List<StudentType> activeStudentType = allStudentType.stream()
				.filter(studentType -> studentType.getIsActive() != null && studentType.getIsActive())
				.collect(Collectors.toList());
		if (activeStudentType.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No active student type");
		}
		return ResponseEntity.ok(activeStudentType);
	}
}
