package com.project.backend_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend_api.model.TeachingAbility;
import com.project.backend_api.repository.TeachingAbilityRepository;
import com.project.backend_api.service.ITeachingAbilityService;

@Service
public class TeachingAbilityServiceImpl implements ITeachingAbilityService{

	@Autowired
    private TeachingAbilityRepository teachingAbilityRepository;

    @Override
    public List<TeachingAbility> getAllTeachingAbility() {
        return teachingAbilityRepository.findAll();
    }

    @Override
    public Optional<TeachingAbility> getTeachingAbilityById(Long id) {
        return teachingAbilityRepository.findById(id);
    }

    @Override
    public TeachingAbility saveTeachingAbility(TeachingAbility teachingAbility) {
        return teachingAbilityRepository.save(teachingAbility);
    }

    @Override
    public TeachingAbility updateTeachingAbility(Long id, TeachingAbility teachingAbility) {
        TeachingAbility existingTeachingAbility = teachingAbilityRepository.findById(id).orElseThrow(null);
        if (existingTeachingAbility != null){
            existingTeachingAbility.setTeachingAssignments(teachingAbility.getTeachingAssignments());
            existingTeachingAbility.setTopic(teachingAbility.getTopic());
            return teachingAbilityRepository.save(existingTeachingAbility);
        }
        return null;
    }

    @Override
    public void deleteTeachingAbility(Long id) {
        teachingAbilityRepository.deleteById(id);
    }

}
