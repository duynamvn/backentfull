package com.project.backend_api.mapper;

import com.project.backend_api.dto.CourseDTO;
import com.project.backend_api.dto.EmployeeDTO;
import com.project.backend_api.dto.SessionDTO;
import com.project.backend_api.dto.TeachingAbilityDTO;
import com.project.backend_api.dto.TeachingAssignmentDTO;
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
            dto.setCourse(courseDTO);
        }

        TeachingAbility teachingAbility = teachingAssignment.getTeachingAbility();
        if (teachingAbility != null){
            TeachingAbilityDTO teachingAbilityDTO = new TeachingAbilityDTO();
            teachingAbilityDTO.setId(teachingAbility.getId());
            dto.setTeachingAbility(teachingAbilityDTO);
        }

        return dto;
    }
}
