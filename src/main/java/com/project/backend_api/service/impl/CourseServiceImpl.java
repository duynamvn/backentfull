package com.project.backend_api.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.project.backend_api.dto.StudentDTO;
import com.project.backend_api.mapper.StudentMapper;
import com.project.backend_api.model.TuitionFee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend_api.model.Course;
import com.project.backend_api.model.Topic;
import com.project.backend_api.repository.CourseRepository;
import com.project.backend_api.repository.TopicRepository;
import com.project.backend_api.service.ICourseService;

@Slf4j
@Service
public class CourseServiceImpl implements ICourseService{

	@Autowired
    private CourseRepository courseRepository;
	
	@Autowired
	private TopicRepository topicRepository;

    @Override
    public Course saveCourse(Course course) {
        log.info("Attempting to save course: {}", course.getCourseName());
        try {
            if (course.getTopic() != null && course.getTopic().getId() != null) {
                Topic topic = topicRepository.findById(course.getTopic().getId()).orElseThrow();
                course.setTopic(topic);
                log.info("Found and set topic for course: {}", course.getCourseName());
            }
            Course savedCourse = courseRepository.save(course);
            log.info("Successfully saved course with ID: {}", savedCourse.getId());
            return savedCourse;
        } catch (Exception e) {
            log.error("Error saving course: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        log.info("Attempting to update course with ID: {}", id);
        try {
            Course existingCourse = courseRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
            log.info("Found existing course with ID: {}", id);

            existingCourse.setCourseCode(course.getCourseCode());
            existingCourse.setCourseName(course.getCourseName());
            existingCourse.setStartDate(course.getStartDate());
            existingCourse.setEndDate(course.getEndDate());
            existingCourse.setTotalStudent(course.getTotalStudent());
            existingCourse.setActivate(course.getActivate());
            existingCourse.setSession(course.getSession());
            existingCourse.setTopic(course.getTopic());

            // Kiểm tra xem session có tồn tại hay không
            if (course.getSession() != null && course.getSession().getId() != null) {
                existingCourse.setSession(course.getSession());
                log.info("Updated session for course ID: {}", id);
            }

            if (course.getTopic() != null && course.getTopic().getId() != null) {
                existingCourse.setTopic(course.getTopic());
                log.info("Updated topic for course ID: {}", id);
            }
            Course updatedCourse = courseRepository.save(existingCourse);
            log.info("Successfully updated course with ID: {}", id);
            return updatedCourse;
        } catch (Exception e){
            log.error("Error updating course with ID: {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }


    @Override
    public Optional<Course> getCourseById(Long id) {
        log.info("Fetching course with ID: {}", id);
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            log.info("Found course with ID: {}", id);
        } else {
            log.warn("Course not found with ID: {}", id);
        }
        return course;
    }

    @Override
    public List<Course> getAllCourses() {
        log.info("Fetching all courses");
        List<Course> courses = courseRepository.findAll();
        log.info("Found {} courses", courses.size());
        return courses;
    }

    @Override
    public void deleteCourse(Long id) {
        log.info("Deleting course with ID: {}", id);
        try {
            courseRepository.deleteById(id);
            log.info("Successfully deleted course with ID: {}", id);
        } catch (Exception e) {
            log.error("Error deleting course with ID: {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Long getLatestCourseId() {
        log.info("Fetching latest course ID");
        List<Course> courses = courseRepository.findTopCoursesOrderByIdDesc();
        Long latestId  = courses.isEmpty() ? null : courses.get(0).getId();
        log.info("Latest course ID: {}", latestId );
        return latestId ;
    }

    @Override
    public List<Course> getOpenCourses() {
        log.info("Fetching open courses");
        LocalDate currentDate = LocalDate.now();
        List<Course> courses = courseRepository.findOpenCourses(currentDate);
        log.info("Retrieved {} open courses", courses.size());
        return courses;
    }

    @Override
    public List<StudentDTO> getStudentsByCourseId(Long courseId) {
        log.info("Fetching students by course ID: {}", courseId);
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
        List<StudentDTO> studentDTOs = course.getTuitionFees().stream()
                .map(TuitionFee::getStudent)
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
        log.info("Retrieved {} students for course with ID: {}", studentDTOs.size(), courseId);
        return studentDTOs;
    }

    @Override
    public List<Course> getClosedCourses() {
        log.info("Fetching closed courses");
        LocalDate currentDate = LocalDate.now();
        List<Course> courses = courseRepository.findCloseCourses(currentDate);
        log.info("Retrieved {} closed courses", courses.size());
        return courses;
    }

}
