package com.project.backend_api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend_api.dto.TeachingAssignmentDTO;
import com.project.backend_api.mapper.TeachingAssignmentMapper;
import com.project.backend_api.model.TeachingAssignment;
import com.project.backend_api.service.ITeachingAssignmentService;

@RestController
@RequestMapping("/api/teaching-assignment")
public class TeachingAssignmentController {

    @Autowired
    private ITeachingAssignmentService iTeachingAssignmentService;

    @GetMapping
    public List<TeachingAssignmentDTO> getAllTeachingAssignments() {
        List<TeachingAssignment> teachingAssignments = iTeachingAssignmentService.getAllTeachingAssignments();
        return teachingAssignments.stream()
                .map(TeachingAssignmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TeachingAssignmentDTO getTeachingAssignmentById(@PathVariable Long id) {
        Optional<TeachingAssignment> teachingAssignment = iTeachingAssignmentService.getTeachingAssignmentById(id);
        return teachingAssignment.map(TeachingAssignmentMapper::toDTO).orElse(null);
    }

    @PostMapping
    public ResponseEntity<?> createTeachingAssignment(@RequestBody TeachingAssignment teachingAssignment) {
        try {
            TeachingAssignment savedAssignment = iTeachingAssignmentService.saveTeachingAssignment(teachingAssignment);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAssignment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating teaching assignment: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeachingAssignment(@PathVariable Long id, @RequestBody TeachingAssignment teachingAssignment) {
        try {
            TeachingAssignment updatedAssignment = iTeachingAssignmentService.updateTeachingAssignment(id, teachingAssignment);
            return ResponseEntity.status(HttpStatus.OK).body(updatedAssignment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error updating teaching assignment: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTeachingAssignment(@PathVariable Long id) {
        iTeachingAssignmentService.deleteTeachingAssignment(id);
    }

}
