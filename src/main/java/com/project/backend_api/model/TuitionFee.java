package com.project.backend_api.model;

import java.time.LocalDate;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tuitionFee")
public class TuitionFee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate registrationDate;

    private LocalDate collectionDate;

    private String note;

    private Boolean collectedMoney;

    private Boolean activate;

    private Boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference(value = "tuitionCourse")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_id") // tên cột trong bảng
    @JsonBackReference(value = "student_tuition")
    private Student student;

    public TuitionFee(Long id){
        this.id = id;
    }
    public Boolean isActive() {
        return isActive;
    }
}