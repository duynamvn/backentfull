package com.project.backend_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teachingAssignment")
public class TeachingAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "activate")
    private Boolean activate;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference(value = "emp_teaching")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference(value = "course_teaching")
    private Course course;

    // Mối quan hệ N:1 với SubjectAssignment
    @ManyToOne
    @JoinColumn(name = "teachingAbility_id") // Khóa ngoại liên kết đến SubjectAssignment
    @JsonBackReference(value = "assign_ability")
    private TeachingAbility teachingAbility; // Môn học được phân công giảng dạy

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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public TeachingAbility getTeachingAbility() {
		return teachingAbility;
	}

	public void setTeachingAbility(TeachingAbility teachingAbility) {
		this.teachingAbility = teachingAbility;
	}
    
    
}
