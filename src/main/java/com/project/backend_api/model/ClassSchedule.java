package com.project.backend_api.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "classSchedule")
public class ClassSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate classDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private Boolean isActive = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id")
    @JsonBackReference(value = "scheduleRoom")
    private ClassRoom classRoom;

    @ManyToOne // Nhiều schedule có thể thuộc về một course
    @JoinColumn(name = "course_id") // Khóa ngoại liên kết đến Course
    @JsonBackReference(value = "scheduleCourse")
    private Course course; // Liên kết với Course
    
    @OneToMany(mappedBy = "classSchedule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "attendClass")
	private List<Attendance> attendances;

    public Boolean isActive(){
        return isActive;
    }
}