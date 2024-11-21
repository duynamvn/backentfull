package com.project.backend_api.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.project.backend_api.dto.CourseDateDTO;
import com.project.backend_api.dto.StudentDTO;
import com.project.backend_api.model.Course;

public interface ICourseService {

	Course saveCourse(Course course);           // Thêm Course mới
    Course updateCourse(Long id, Course course); // Cập nhật Course
    Optional<Course> getCourseById(Long id);               // Lấy Course theo ID
    List<Course> getAllCourses();                // Lấy tất cả Course
    void deleteCourse(Long id);
    Long getLatestCourseId();

    List<Course> getOpenCourses(); // Những khóa học đang mở
    List<StudentDTO> getStudentsByCourseId(Long courseId); // Tìm danh sách học viên theo khóa học
    List<Course> getClosedCourses();

    Map<String, Long> countCoursesByStatus();
    List<CourseDateDTO> getAllCourseDates();

    void updateIsActiveStatus(Long id, Boolean isActive);
}
