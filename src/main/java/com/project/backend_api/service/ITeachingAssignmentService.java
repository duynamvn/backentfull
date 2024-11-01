package com.project.backend_api.service;

import java.util.List;
import java.util.Optional;

import com.project.backend_api.model.TeachingAssignment;

public interface ITeachingAssignmentService {

	List<TeachingAssignment> getAllTeachingAssignments();
    Optional<TeachingAssignment> getTeachingAssignmentById(Long id);
    TeachingAssignment saveTeachingAssignment(TeachingAssignment teachingAssignment);
    TeachingAssignment updateTeachingAssignment(Long id, TeachingAssignment teachingAssignment); // Thêm phương thức update
    void deleteTeachingAssignment(Long id);
    
}
