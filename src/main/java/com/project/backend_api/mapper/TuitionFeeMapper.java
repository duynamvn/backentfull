package com.project.backend_api.mapper;

import com.project.backend_api.dto.CourseDTO;
import com.project.backend_api.dto.SessionDTO;
import com.project.backend_api.dto.TopicDTO;
import com.project.backend_api.dto.TuitionFeeDTO;
import com.project.backend_api.model.Course;
import com.project.backend_api.model.TuitionFee;

public class TuitionFeeMapper {

    public static TuitionFeeDTO toDto(TuitionFee tuitionFee){
        TuitionFeeDTO dto = new TuitionFeeDTO();
        dto.setId(tuitionFee.getId());
        dto.setRegistrationDate(tuitionFee.getRegistrationDate());
        dto.setCollectionDate(tuitionFee.getCollectionDate());
        dto.setNote(tuitionFee.getNote());
        dto.setCollectedMoney(tuitionFee.getCollectedMoney());
        dto.setActivate(tuitionFee.getActivate());

        Course course = tuitionFee.getCourse();
        if (course != null){
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(course.getId());
            courseDTO.setCourseCode(course.getCourseCode());
            courseDTO.setCourseName(course.getCourseName());
            courseDTO.setStartDate(course.getStartDate());
            courseDTO.setEndDate(course.getEndDate());
            courseDTO.setTotalStudent(course.getTotalStudent());
            courseDTO.setActivate(course.getActivate());
            if (course.getSession() != null){
                SessionDTO sessionDTO = new SessionDTO();
                sessionDTO.setId(course.getId());
                sessionDTO.setSessionName(course.getSession().getSessionName());
                sessionDTO.setTimeSlot(course.getSession().getTimeSlot());
                courseDTO.setSession(sessionDTO);
            }
            if (course.getTopic() !=null){
                TopicDTO topicDTO = new TopicDTO();
                topicDTO.setId(course.getId());
                topicDTO.setTopicCode(course.getCourseCode());
                topicDTO.setTopicName(course.getCourseName());
                topicDTO.setTheoryHours(course.getTotalStudent());
                topicDTO.setPracticalHours(course.getTotalStudent());
                topicDTO.setActivate(course.getActivate());
                topicDTO.setOriginalPrice(course.getTopic().getOriginalPrice());
                topicDTO.setPromotionalPrice(course.getTopic().getPromotionalPrice());
                courseDTO.setTopic(topicDTO);
            }
            dto.setCourse(courseDTO);
        }
        if (tuitionFee.getCourse() != null && tuitionFee.getCourse().getTopic() != null){
            dto.setOriginalPrice(tuitionFee.getCourse().getTopic().getOriginalPrice());
            dto.setPromotionalPrice(tuitionFee.getCourse().getTopic().getPromotionalPrice());
        }

        return dto;
    }
}
