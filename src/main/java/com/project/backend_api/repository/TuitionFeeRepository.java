package com.project.backend_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.backend_api.model.TuitionFee;

import java.util.List;

@Repository
public interface TuitionFeeRepository extends JpaRepository<TuitionFee, Long>{

    @Query("SELECT t FROM TuitionFee t WHERE t.course.activate = false")
    List<TuitionFee> findAllByCourseActivateFalse();

    @Query("SELECT t FROM TuitionFee t WHERE t.course.id = :courseId AND t.course.activate = false")
    TuitionFee findByCourseAndInactive(Long courseId);
}
