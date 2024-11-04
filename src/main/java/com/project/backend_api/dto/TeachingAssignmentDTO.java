package com.project.backend_api.dto;

import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeachingAssignmentDTO {
    private Long id;
    private Boolean activate;
    private EmployeeDTO employee;
    private CourseDTO course;
    private TeachingAbilityDTO teachingAbility;

}