package com.project.backend_api.mapper;

import com.project.backend_api.dto.EmployeeDTO;
import com.project.backend_api.dto.TeachingAbilityDTO;
import com.project.backend_api.dto.TopicDTO;
import com.project.backend_api.model.Employee;
import com.project.backend_api.model.TeachingAbility;
import com.project.backend_api.model.Topic;

public class TeachingAbilityMapper {

    public static TeachingAbilityDTO toDto(TeachingAbility teachingAbility){
        TeachingAbilityDTO dto = new TeachingAbilityDTO();
        dto.setId(teachingAbility.getId());

        Topic topics = teachingAbility.getTopic();
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
            dto.setTopic(topicsDTO);
        }

        Employee employee = teachingAbility.getEmployee();
        if (employee != null){
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

        return dto;
    }

}