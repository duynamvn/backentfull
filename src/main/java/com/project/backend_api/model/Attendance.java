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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attendance")
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "date_of_recognition")
	private LocalDate dateOfRecognition;

	@Column(name = "note")
	private String note;
	
	@ManyToOne
    @JoinColumn(name = "student_id")
	@JsonBackReference(value = "attendStudent")
	private Student student; // trường này để tham chiếu đến Student
	
	@ManyToOne
	@JoinColumn(name = "classSchedule_id", nullable = false)
	@JsonBackReference(value = "attendClass")
	private ClassSchedule classSchedule; // trường này để tham chiếu đến ClassSchedule
}
