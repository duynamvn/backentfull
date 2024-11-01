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
	
}
