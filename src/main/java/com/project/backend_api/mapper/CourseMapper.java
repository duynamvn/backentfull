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
            TopicDTO topicDTO = new TopicDTO();
            topicDTO.setId(topics.getId());
            topicDTO.setTopicCode(topics.getTopicCode());
            topicDTO.setTopicName(topics.getTopicName());
            topicDTO.setTheoryHours(topics.getTheoryHours());
            topicDTO.setPracticalHours(topics.getPracticalHours());
            topicDTO.setActivate(topics.getActivate());
            topicDTO.setOriginalPrice(topics.getOriginalPrice());
            topicDTO.setPromotionalPrice(topics.getPromotionalPrice());
            dto.setTopic(topicDTO);
        }
        return dto;
    }
}
