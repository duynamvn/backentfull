package com.project.backend_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.backend_api.model.Course;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

    @Query("SELECT c FROM Course c ORDER BY c.id DESC")
    List<Course> findTopCoursesOrderByIdDesc();

    @Query("SELECT c FROM Course c WHERE c.startDate <= :currentDate AND c.endDate >= :currentDate AND c.activate = true")
    List<Course> findOpenCourses(@Param("currentDate") LocalDate currentDate);

    @Query("SELECT c FROM  Course c WHERE c.endDate <= :currentDate")
    List<Course> findCloseCourses(@Param("currentDate") LocalDate currentDate);
}
