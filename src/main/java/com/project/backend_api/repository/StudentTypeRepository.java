package com.project.backend_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.backend_api.model.StudentType;

@Repository
public interface StudentTypeRepository extends JpaRepository<StudentType, Long>{

}
