package com.project.backend_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeachingAssignmentDTO {
    private Long id;
    private Boolean activate;
    private EmployeeDTO employee;
    private CourseDTO course;
    private TeachingAbilityDTO teachingAbility;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getActivate() {
		return activate;
	}
	public void setActivate(Boolean activate) {
		this.activate = activate;
	}
	public EmployeeDTO getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}
	public CourseDTO getCourse() {
		return course;
	}
	public void setCourse(CourseDTO course) {
		this.course = course;
	}
	public TeachingAbilityDTO getTeachingAbility() {
		return teachingAbility;
	}
	public void setTeachingAbility(TeachingAbilityDTO teachingAbility) {
		this.teachingAbility = teachingAbility;
	}
}