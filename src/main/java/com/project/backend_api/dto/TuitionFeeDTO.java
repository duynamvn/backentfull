package com.project.backend_api.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TuitionFeeDTO {

    private Long id;
    private LocalDate registrationDate;
    private LocalDate collectionDate;
    private String note;
    private Boolean collectedMoney;
    private Boolean activate;
    private CourseDTO course;
    
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
	public CourseDTO getCourse() {
		return course;
	}
	public void setCourse(CourseDTO course) {
		this.course = course;
	}
}
