package com.project.backend_api.service.impl;

import java.util.List;
import java.util.Optional;

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
        TeachingAssignment existingTeachingAssignment = teachingAssignmentRepository.findById(id).orElseThrow(null);
        if (existingTeachingAssignment != null){
            existingTeachingAssignment.setActivate(teachingAssignment.getActivate());
            existingTeachingAssignment.setEmployee(teachingAssignment.getEmployee());
            existingTeachingAssignment.setCourse(teachingAssignment.getCourse());
            existingTeachingAssignment.setTeachingAbility(teachingAssignment.getTeachingAbility());
            return teachingAssignmentRepository.save(existingTeachingAssignment);
        }
        return null;
    }

    @Override
    public void deleteTeachingAssignment(Long id) {
        teachingAssignmentRepository.deleteById(id);
    }

    
}
