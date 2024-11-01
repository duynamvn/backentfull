package com.project.backend_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.backend_api.model.Specialization;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long>{

}
