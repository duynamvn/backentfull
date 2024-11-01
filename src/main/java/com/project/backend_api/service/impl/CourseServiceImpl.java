package com.project.backend_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend_api.model.Course;
import com.project.backend_api.model.Topic;
import com.project.backend_api.repository.CourseRepository;
import com.project.backend_api.repository.TopicRepository;
import com.project.backend_api.service.ICourseService;

@Service
public class CourseServiceImpl implements ICourseService{

	@Autowired
    private CourseRepository courseRepository;
	
	@Autowired
	private TopicRepository topicRepository;

    @Override
    public Course saveCourse(Course course) {
    	if (course.getTopic() != null && course.getTopic().getId() != null) {
    		Topic topic = topicRepository.findById(course.getTopic().getId()).orElseThrow();
    		course.setTopic(topic);
    	}
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

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
        } 
        
        if (course.getTopic() != null && course.getTopic().getId() != null) {
        	existingCourse.setTopic(course.getTopic());
        }

        return courseRepository.save(existingCourse);
    }


    @Override
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

}
