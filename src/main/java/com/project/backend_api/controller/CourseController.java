package com.project.backend_api.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.project.backend_api.dto.CourseDateDTO;
import com.project.backend_api.dto.StudentDTO;
import com.project.backend_api.model.Student;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
@CrossOrigin(origins = "http://localhost:5173")
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

            // Kiểm tra đầu vào
            if (courseDetails == null) {
                return ResponseEntity.badRequest().body("Course details cannot be null");
            }
            if (courseDetails.getCourseName() == null || courseDetails.getCourseName().isEmpty()) {
                return ResponseEntity.badRequest().body("Course name is required");
            }
            if (courseDetails.getStartDate() == null || courseDetails.getEndDate() == null) {
                return ResponseEntity.badRequest().body("Start date and end date are required");
            }

            Course existingCourse = iCourseService.getCourseById(id)
                    .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

            LocalDate today = LocalDate.now();
            // Kiểm tra nếu khóa học đã bắt đầu hoặc kết thúc
            if (!today.isBefore(existingCourse.getStartDate()) && !today.isAfter(existingCourse.getEndDate())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Course has already started or ended and cannot be updated");
            }

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

    @Valid
    @PostMapping("/addCourse")
    public ResponseEntity<?> addCourse(@RequestBody Course course, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
        String courseCode = generateCourseCode(course.getCourseName());
        course.setCourseCode(courseCode);
        iCourseService.saveCourse(course);
        return ResponseEntity.ok("Course added successfully");
    }
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivateCourse(@PathVariable("id") Long id) {
        try {
            log.info("Attempting to deactivate course with ID: {}", id);

            // Lấy thông tin khóa học
            Course currentCourse = iCourseService.getCourseById(id)
                    .orElseThrow(() -> new RuntimeException("Course not found with ID: " + id));

            LocalDate today = LocalDate.now();

            // Kiểm tra nếu khóa học đang mở và đã kết thúc
            if (!today.isBefore(currentCourse.getStartDate()) && today.isAfter(currentCourse.getEndDate())) {
                log.warn("Course with ID: {} is currently open and already ended. Deactivation not allowed.", id);
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Course cannot be deactivated because it is currently open and already ended.");
            }

            // Cho phép hủy kích hoạt nếu không vi phạm điều kiện trên
            currentCourse.setActivate(false);
            iCourseService.saveCourse(currentCourse);

            log.info("Course with ID: {} successfully deactivated.", id);
            return ResponseEntity.ok("Course deactivated successfully.");
        } catch (RuntimeException e) {
            log.error("Error while deactivating course with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error occurred while deactivating course with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }
    @GetMapping("/active")
    public ResponseEntity<?> getAllActiveCourses() {
        List<Course> allCourses = iCourseService.getAllCourses();
        if (allCourses == null || allCourses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<Course> activeCourses = allCourses.stream().
                filter(course -> course.getIsActive() != null && course.getIsActive())
                .collect(Collectors.toList());
        if (activeCourses.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No active courses");
        }
        List<CourseDTO> courseDTOs = activeCourses.stream().map(CourseMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(courseDTOs);
    }
}
