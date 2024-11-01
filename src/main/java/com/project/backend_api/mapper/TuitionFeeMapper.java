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
                TopicDTO topicsDTO = new TopicDTO();
                topicsDTO.setId(course.getId());
                topicsDTO.setTopicCode(course.getCourseCode());
                topicsDTO.setTopicName(course.getCourseName());
                topicsDTO.setTheoryHours(course.getTotalStudent());
                topicsDTO.setPracticalHours(course.getTotalStudent());
                topicsDTO.setActivate(course.getActivate());
                topicsDTO.setOriginalPrice(course.getTopic().getOriginalPrice());
                topicsDTO.setPromotionalPrice(course.getTopic().getPromotionalPrice());
                courseDTO.setTopics(topicsDTO);
            }
            dto.setCourse(courseDTO);
        }
        return dto;
    }
}
