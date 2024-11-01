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

import com.project.backend_api.model.Specialization;
import com.project.backend_api.service.ISpecializationService;

@RestController
@RequestMapping("/api/specialization")
public class SpecializationController {

	@Autowired
	private  ISpecializationService iSpecializationService;
	
	@GetMapping
	public ResponseEntity<List<Specialization>> getAllSpecialization(){
		List<Specialization> specializations = iSpecializationService.getAllSpecialization();
		return ResponseEntity.ok(specializations);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Specialization> getSpecializationById(@PathVariable Long id){
		Specialization specialization = iSpecializationService.getSpecializationById(id).orElseThrow();
		return ResponseEntity.ok(specialization);
	}
	
	@PostMapping
	public ResponseEntity<Specialization> createSpecialization(@RequestBody Specialization specialization){
		Specialization createSpecialization = iSpecializationService.createSpecialization(specialization);
		return ResponseEntity.ok(createSpecialization);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Specialization> updateSpecialization(@PathVariable Long id, @RequestBody Specialization specialization){
		Specialization updateSpecialization = iSpecializationService.updateSpecialization(id, specialization);
		updateSpecialization.setSpecializationName(specialization.getSpecializationName());
		return ResponseEntity.ok(specialization);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Specialization> deleteSpecialization(@PathVariable Long id){
		iSpecializationService.deleteSpecialization(id);
		return ResponseEntity.noContent().build();
	}
}
