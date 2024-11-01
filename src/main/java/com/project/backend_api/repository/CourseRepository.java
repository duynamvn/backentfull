package com.project.backend_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.backend_api.model.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

    @Query("SELECT c FROM Course c ORDER BY c.id DESC")
    List<Course> findTopCoursesOrderByIdDesc();
}
