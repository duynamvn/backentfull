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
    
    @Column(name = "registration_date")
    private LocalDate registrationDate;
    
    @Column(name = "collection_date")
    private LocalDate collectionDate;
    
    @Column(name = "note")
    private String note;
    
    @Column(name = "collected_money")
    private Boolean collectedMoney;
    
    @Column(name = "activate")
    private Boolean activate;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference(value = "tuitionCourse")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_id") // tên cột trong bảng
    @JsonBackReference(value = "student_tuition")
    private Student student;
    
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public LocalDate getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(LocalDate collectionDate) {
		this.collectionDate = collectionDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Boolean getCollectedMoney() {
		return collectedMoney;
	}

	public void setCollectedMoney(Boolean collectedMoney) {
		this.collectedMoney = collectedMoney;
	}

	public Boolean getActivate() {
		return activate;
	}

	public void setActivate(Boolean activate) {
		this.activate = activate;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}