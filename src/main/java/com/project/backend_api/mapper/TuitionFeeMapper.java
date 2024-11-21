package com.project.backend_api.mapper;

import com.project.backend_api.dto.*;
import com.project.backend_api.model.Course;
import com.project.backend_api.model.Student;
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

        Student student = tuitionFee.getStudent();
        if (student != null && Boolean.TRUE.equals(tuitionFee.getStudent().getIsActive()) ) {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(student.getId());
            studentDTO.setStudentCode(student.getStudentCode());
            studentDTO.setFullName(student.getFullName());
            studentDTO.setDateOfBirth(student.getDateOfBirth());
            studentDTO.setAddress(student.getAddress());
            studentDTO.setPhoneNumber(student.getPhoneNumber());
            studentDTO.setGender(student.getGender());
            studentDTO.setEmail(student.getEmail());
            studentDTO.setNationalID(student.getNationalID());
            studentDTO.setImageName(student.getImageName());
            studentDTO.setIsActive(student.getIsActive());
            if (student.getStudentType() != null) {
                StudentTypeDTO studentTypeDTO = new StudentTypeDTO();
                studentTypeDTO.setId(student.getStudentType().getId());
                studentTypeDTO.setStudentTypeName(student.getStudentType().getStudentTypeName());
                studentDTO.setStudentType(studentTypeDTO);
            }
            dto.setStudent(studentDTO);
        } else {
            dto.setStudent(null);
        }

        Course course = tuitionFee.getCourse();
        if (course != null && Boolean.TRUE.equals(tuitionFee.getCourse().getIsActive()) ) {
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
        } else {
            dto.setCourse(null);
        }
        if (tuitionFee.getCourse() != null && tuitionFee.getCourse().getTopic() != null){
            dto.setOriginalPrice(tuitionFee.getCourse().getTopic().getOriginalPrice());
            dto.setPromotionalPrice(tuitionFee.getCourse().getTopic().getPromotionalPrice());
        }

        return dto;
    }
}
