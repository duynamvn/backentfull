package com.project.backend_api.service;

import java.util.List;
import java.util.Optional;

import com.project.backend_api.model.Course;

public interface ICourseService {

	Course saveCourse(Course course);           // Thêm Course mới
    Course updateCourse(Long id, Course course); // Cập nhật Course
    Optional<Course> getCourseById(Long id);               // Lấy Course theo ID
    List<Course> getAllCourses();                // Lấy tất cả Course
    void deleteCourse(Long id); 
}
