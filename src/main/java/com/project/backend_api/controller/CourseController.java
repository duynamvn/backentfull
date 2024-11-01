package com.project.backend_api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend_api.dto.CourseDTO;
import com.project.backend_api.mapper.CourseMapper;
import com.project.backend_api.model.Course;
import com.project.backend_api.service.ICourseService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private ICourseService iCourseService;

    // Thêm Course mới
    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        try {
            String generateCourseCode = generateCourseCode(course.getCourseName());
            course.setCourseCode(generateCourseCode);
            Course existingCourse = iCourseService.saveCourse(course);
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Course code already exists.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }

    }

    private String generateCourseCode(String courseName) {

        String[] words = courseName.split(" ");
        StringBuilder code = new StringBuilder();
        for (String word : words) {
            code.append(word.charAt(0));
        }

        Long latestId = iCourseService.getLatestCourseId();
        int newId = latestId != null ? (int) (latestId + 1) : 1;

        return code.toString().toUpperCase() + String.format("%02d", newId);
    }

    // Cập nhật Course theo ID
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        return iCourseService.updateCourse(id, courseDetails);
    }

    // Lấy Course theo ID
    @GetMapping("/{id}")
    public CourseDTO getCourseById(@PathVariable Long id) {
        Optional<Course> course = iCourseService.getCourseById(id);
        return course.map(CourseMapper::toDto).orElse(null);
    }

    // Lấy tất cả các Course
    @GetMapping
    public List<CourseDTO>  getAllCourses() {
        List<Course> courses = iCourseService.getAllCourses();
        return courses.stream()
                .map(CourseMapper::toDto)
                .collect(Collectors.toList());
    }

    // Xóa Course theo ID
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        iCourseService.deleteCourse(id);
    }

    @GetMapping("/open")
    public List<CourseDTO> getOpenCourses() {
        List<Course> courses = iCourseService.getOpenCourses();
        return courses.stream().map(CourseMapper::toDto).collect(Collectors.toList());
    }

}
