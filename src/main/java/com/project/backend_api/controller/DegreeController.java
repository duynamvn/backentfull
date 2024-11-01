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

import com.project.backend_api.model.Degree;
import com.project.backend_api.service.IDegreeService;

@RestController
@RequestMapping("/api/degree")
public class DegreeController {

	@Autowired
	private IDegreeService iDegreeService;
	
	@GetMapping
	public ResponseEntity<List<Degree>> getAllDegree(){
		List<Degree> degree = iDegreeService.getAllDegree();
		return ResponseEntity.ok(degree);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Degree> getDegreeById(@PathVariable Long id){
		Degree degree = iDegreeService.getDegreeById(id).orElseThrow();
		return ResponseEntity.ok(degree);
	}
	
	@PostMapping
	public ResponseEntity<Degree> createDegree(@RequestBody Degree degree){
		Degree createDegree = iDegreeService.createDegree(degree);
		return ResponseEntity.ok(createDegree);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Degree> updateDegree(@PathVariable Long id, @RequestBody Degree degree){
		Degree updateDegree = iDegreeService.updateDegree(id, degree);
		updateDegree.setNote(degree.getNote());
		updateDegree.setDegreeName(degree.getDegreeName());
		return ResponseEntity.ok(degree);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Degree> deleteDegree(@PathVariable Long id){
		iDegreeService.deleteDegree(id);
		return ResponseEntity.noContent().build();
	}
}
