package com.project.backend_api.mapper;

import com.project.backend_api.dto.*;
import com.project.backend_api.model.Course;
import com.project.backend_api.model.Employee;
import com.project.backend_api.model.TeachingAbility;
import com.project.backend_api.model.TeachingAssignment;

public class TeachingAssignmentMapper {

    public static TeachingAssignmentDTO toDTO(TeachingAssignment teachingAssignment) {
        TeachingAssignmentDTO dto = new TeachingAssignmentDTO();
        dto.setId(teachingAssignment.getId());
        dto.setActivate(teachingAssignment.getActivate());

        Employee employee = teachingAssignment.getEmployee();
        if (employee != null) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setFullName(employee.getFullName());
            employeeDTO.setDateOfBirth(employee.getDateOfBirth());
            employeeDTO.setGender(employee.getGender());
            employeeDTO.setNationalID(employee.getNationalID());
            employeeDTO.setEmail(employee.getEmail());
            employeeDTO.setPhoneNumber(employee.getPhoneNumber());
            employeeDTO.setActivate(employee.getActivate());
            employeeDTO.setAddress(employee.getAddress());
            employeeDTO.setImageName(employee.getImageName());
            dto.setEmployee(employeeDTO);

            if (employee.getEmployeeType() != null) {
                EmployeeTypeDTO employeeTypeDTO = new EmployeeTypeDTO();
                employeeTypeDTO.setId(employee.getEmployeeType().getId());
                employeeTypeDTO.setEmployeeTypeName(employee.getEmployeeType().getEmployeeTypeName());
                employeeDTO.setEmployeeType(employeeTypeDTO);
            }
            dto.setEmployee(employeeDTO);
        }

        Course course = teachingAssignment.getCourse();
        if (course != null) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(course.getId());
            courseDTO.setCourseCode(course.getCourseCode());
            courseDTO.setCourseName(course.getCourseName());
            courseDTO.setStartDate(course.getStartDate());
            courseDTO.setEndDate(course.getEndDate());
            courseDTO.setTotalStudent(course.getTotalStudent());
            courseDTO.setActivate(course.getActivate());
            dto.setCourse(courseDTO);

            if (course.getSession() != null){
                SessionDTO sessionDTO = new SessionDTO();
                sessionDTO.setId(course.getId());
                sessionDTO.setSessionName(course.getSession().getSessionName());
                sessionDTO.setTimeSlot(course.getSession().getTimeSlot());
                courseDTO.setSession(sessionDTO);
            }

            if (course.getTopic() != null){
                TopicDTO topicDTO = new TopicDTO();
                topicDTO.setId(course.getId());
                topicDTO.setTopicName(course.getTopic().getTopicName());
                topicDTO.setTopicCode(course.getTopic().getTopicCode());
                topicDTO.setTheoryHours(course.getTopic().getTheoryHours());
                topicDTO.setPracticalHours(course.getTopic().getPracticalHours());
                topicDTO.setActivate(course.getTopic().getActivate());
                topicDTO.setOriginalPrice(course.getTopic().getOriginalPrice());
                topicDTO.setPromotionalPrice(course.getTopic().getPromotionalPrice());
                courseDTO.setTopic(topicDTO);
            }

            dto.setCourse(courseDTO);
        }

        TeachingAbility teachingAbility = teachingAssignment.getTeachingAbility();
        if (teachingAbility != null){
            TeachingAbilityDTO teachingAbilityDTO = new TeachingAbilityDTO();
            teachingAbilityDTO.setId(teachingAbility.getId());

            if (teachingAbility.getTopic() != null){
                TopicDTO topicDTO = new TopicDTO();
                topicDTO.setId(teachingAbility.getId());
                topicDTO.setTopicName(teachingAbility.getTopic().getTopicName());
                topicDTO.setTopicCode(teachingAbility.getTopic().getTopicCode());
                topicDTO.setTheoryHours(teachingAbility.getTopic().getTheoryHours());
                topicDTO.setPracticalHours(teachingAbility.getTopic().getPracticalHours());
                topicDTO.setActivate(teachingAbility.getTopic().getActivate());
                topicDTO.setOriginalPrice(teachingAbility.getTopic().getOriginalPrice());
                topicDTO.setPromotionalPrice(teachingAbility.getTopic().getPromotionalPrice());
                teachingAbilityDTO.setTopic(topicDTO);
            }

            dto.setTeachingAbility(teachingAbilityDTO);
        }

        return dto;
    }
}
