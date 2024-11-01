package com.project.backend_api.mapper;

import com.project.backend_api.dto.CourseDTO;
import com.project.backend_api.dto.SessionDTO;
import com.project.backend_api.dto.TopicDTO;
import com.project.backend_api.model.Course;
import com.project.backend_api.model.Sessions;
import com.project.backend_api.model.Topic;

public class CourseMapper {

    public static CourseDTO toDto(Course course){
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setCourseCode(course.getCourseCode());
        dto.setCourseName(course.getCourseName());
        dto.setStartDate(course.getStartDate());
        dto.setEndDate(course.getEndDate());
        dto.setTotalStudent(course.getTotalStudent());
        dto.setActivate(course.getActivate());

        Sessions session = course.getSession();
        if (session != null){
            SessionDTO sessionDTO = new SessionDTO();
            sessionDTO.setId(session.getId());
            sessionDTO.setTimeSlot(session.getTimeSlot());
            sessionDTO.setSessionName(session.getSessionName());
            dto.setSession(sessionDTO);
        }

        Topic topics = course.getTopic();
        if (topics != null){
            TopicDTO topicsDTO = new TopicDTO();
            topicsDTO.setId(topics.getId());
            topicsDTO.setTopicCode(topics.getTopicCode());
            topicsDTO.setTopicName(topics.getTopicName());
            topicsDTO.setTheoryHours(topics.getTheoryHours());
            topicsDTO.setPracticalHours(topics.getPracticalHours());
            topicsDTO.setActivate(topics.getActivate());
            topicsDTO.setOriginalPrice(topics.getOriginalPrice());
            topicsDTO.setPromotionalPrice(topics.getPromotionalPrice());
            dto.setTopics(topicsDTO);
        }
        return dto;
    }
}
