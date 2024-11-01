package com.project.backend_api.service;

import java.util.List;
import java.util.Optional;

import com.project.backend_api.model.Degree;

public interface IDegreeService {

	List<Degree> getAllDegree();
	Optional<Degree> getDegreeById(Long id);
	Degree createDegree(Degree degree);
	Degree updateDegree(Long id, Degree degree);
	void deleteDegree(Long id);
}
