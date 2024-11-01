package com.project.backend_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.backend_api.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
