package com.project.backend_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.project.backend_api.model.Degree;

@Service
public interface DegreeRepository extends JpaRepository<Degree, Long>{

}
