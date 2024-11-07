package com.project.backend_api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.backend_api.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

    Optional<Student> findByEmail(String email);
    Optional<Student> findByNationalID(String nationalID);
    Optional<Student> findStudentByPhoneNumber(String phoneNumber);

    Optional<Student> findByStudentCode(String studentCode);

    @Query("SELECT c FROM Student c ORDER BY c.id DESC")
    List<Student> findTopStudentsOrderByIdDesc();

    @Query("SELECT s FROM Student s WHERE s.fullName LIKE %:fullName%")
    List<Student> findStudentByFullName(@Param("fullName") String fullName);
}
