package com.project.backend_api.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.project.backend_api.dto.CourseDateDTO;
import com.project.backend_api.dto.StudentDTO;
import com.project.backend_api.model.Student;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private ICourseService iCourseService;


    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        try {
            String generateCourseCode = generateCourseCode(course.getCourseName());
            course.setCourseCode(generateCourseCode);
            Course existingCourse = iCourseService.saveCourse(course);
            return ResponseEntity.status(HttpStatus.CREATED).body(existingCourse);
        } catch (IllegalArgumentException e) {
            log.error("Invalid course data: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error occurred while creating course: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }

    private String generateCourseCode(String courseName) {
        // Kiểm tra xem courseName có null không
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be null or empty");
        }

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
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        try {
            log.info("Updating course with id: {}", id);
            Course existingCourse = iCourseService.getCourseById(id)
                    .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
            // Kiểm tra nếu tên khóa học bị thay đổi
            if (!existingCourse.getCourseName().equals(courseDetails.getCourseName())) {
                // Tạo mã khóa học mới
                String newCourseCode = generateCourseCode(courseDetails.getCourseName());
                courseDetails.setCourseCode(newCourseCode);
                log.info("Course name changed. New course code generated: {}", newCourseCode);
            } else {
                // Giữ nguyên mã khóa học hiện tại
                courseDetails.setCourseCode(existingCourse.getCourseCode());
                log.info("Course name not changed. Retaining current course code: {}", existingCourse.getCourseCode());
            }

            // Cập nhật các trường khác
            courseDetails.setId(existingCourse.getId()); // Đảm bảo đặt ID cho bản ghi cần cập nhật
            Course updateCourse = iCourseService.updateCourse(id, courseDetails);
            log.info("Course with ID: {} updated successfully", id);
            return ResponseEntity.ok(updateCourse);
        } catch (Exception e) {
            log.error("Error occurred while updating course with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }

    // Lấy Course theo ID
    @GetMapping("/{id}")
    public CourseDTO getCourseById(@PathVariable Long id) {
        log.info("Fetching course with id: {}", id);
        Optional<Course> course = iCourseService.getCourseById(id);
        if (course.isPresent()) {
            log.info("Found course with id: {}", id);
            return CourseMapper.toDto(course.get());
        } else{
            log.warn("Course with id: {} not found", id);
            return null;
        }
    }

    // Lấy tất cả các Course
    @GetMapping
    public List<CourseDTO>  getAllCourses() {
        log.info("Fetching all courses");
        List<Course> courses = iCourseService.getAllCourses();
        log.info("Found {} courses", courses.size());
        return courses.stream()
                .map(CourseMapper::toDto)
                .collect(Collectors.toList());
    }

    // Xóa Course theo ID
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        log.info("Deleting course with id: {}", id);
        iCourseService.deleteCourse(id);
        log.info("Course with id: {} deleted successfully", id);
    }

    @GetMapping("/open")
    public List<CourseDTO> getOpenCourses() {
        log.info("Fetching all open courses");
        List<Course> courses = iCourseService.getOpenCourses();
        log.info("Found {} open courses", courses.size());
        return courses.stream().map(CourseMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<List<StudentDTO>> getStudentsByCourseId(@PathVariable Long id) {
        log.info("Fetching students with id: {}", id);
        List<StudentDTO> students = iCourseService.getStudentsByCourseId(id);
        log.info("Found {} students", students.size());
        return ResponseEntity.ok(students);
    }
    @GetMapping("/close")
    public List<CourseDTO> getCloseCourses() {
        log.info("Fetching close courses");
        List<Course> courses = iCourseService.getClosedCourses();
        log.info("Found {} closed courses", courses.size());
        return courses.stream().map(CourseMapper::toDto).collect(Collectors.toList());
    }

    //Thống kê khóa học đóng, mở
    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> countCoursesByStatus(){
        log.info("Count courses open and close");
        Map<String, Long> courses = iCourseService.countCoursesByStatus();
        return ResponseEntity.ok(courses);
    }

    //Thống kê thời gian bất đầu và thời gian kết thúc
    @GetMapping("/date")
    public ResponseEntity<List<CourseDateDTO>> getAllCourseDates() {
        List<CourseDateDTO> courseDates = iCourseService.getAllCourseDates();
        return ResponseEntity.ok(courseDates);
    }
}
