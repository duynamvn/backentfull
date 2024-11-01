package com.project.backend_api.service;

import java.util.List;
import java.util.Optional;

import com.project.backend_api.model.TeachingAbility;

public interface ITeachingAbilityService {

	List<TeachingAbility> getAllTeachingAbility();
    Optional<TeachingAbility> getTeachingAbilityById(Long id);
    TeachingAbility saveTeachingAbility(TeachingAbility teachingAbility);
    TeachingAbility updateTeachingAbility(Long id, TeachingAbility teachingAbility); // Thêm phương thức update
    void deleteTeachingAbility(Long id);
}
