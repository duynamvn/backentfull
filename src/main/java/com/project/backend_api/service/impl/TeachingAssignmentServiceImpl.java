package com.project.backend_api.service.impl;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend_api.model.TeachingAssignment;
import com.project.backend_api.repository.TeachingAssignmentRepository;
import com.project.backend_api.service.ITeachingAssignmentService;

@Service
public class TeachingAssignmentServiceImpl implements ITeachingAssignmentService{

	@Autowired
    private TeachingAssignmentRepository teachingAssignmentRepository;

    @Override
    public List<TeachingAssignment> getAllTeachingAssignments() {
        return teachingAssignmentRepository.findAll();
    }

    @Override
    public Optional<TeachingAssignment> getTeachingAssignmentById(Long id) {
        return teachingAssignmentRepository.findById(id);
    }

    @Override
    public TeachingAssignment saveTeachingAssignment(TeachingAssignment teachingAssignment) {
        return teachingAssignmentRepository.save(teachingAssignment);
    }

    @Override
    public TeachingAssignment updateTeachingAssignment(Long id, TeachingAssignment teachingAssignment) {
        Optional<TeachingAssignment> existingAssignment = teachingAssignmentRepository.findById(id);
        if (existingAssignment.isPresent()) {
            TeachingAssignment updatedAssignment = existingAssignment.get();
            // Cập nhật các trường cần thiết
            updatedAssignment.setActivate(teachingAssignment.getActivate());
            updatedAssignment.setEmployee(teachingAssignment.getEmployee());
            updatedAssignment.setCourse(teachingAssignment.getCourse());
            updatedAssignment.setTeachingAbility(teachingAssignment.getTeachingAbility());
            return teachingAssignmentRepository.save(updatedAssignment);
        } else {
            throw new EntityNotFoundException("Không tìm thấy phân công giảng dạy với ID: " + id);
        }
    }

    @Override
    public void deleteTeachingAssignment(Long id) {
        teachingAssignmentRepository.deleteById(id);
    }

    
}
