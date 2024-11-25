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
import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teachingAssignment")
public class TeachingAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean activate;
    private Boolean isActive = true;
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

    public TeachingAssignment(Long id){
        this.id = id;
    }
    public Boolean isActive(){
        return isActive;
    }
}
