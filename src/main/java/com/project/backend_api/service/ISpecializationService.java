package com.project.backend_api.service;

import java.util.List;
import java.util.Optional;

import com.project.backend_api.model.Specialization;

public interface ISpecializationService {

	List<Specialization> getAllSpecialization();
	Optional<Specialization> getSpecializationById(Long id);
	Specialization createSpecialization(Specialization specialization);
	Specialization updateSpecialization(Long id, Specialization specialization);
	void deleteSpecialization(Long id);
}
