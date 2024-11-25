package com.project.backend_api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "classRoom")
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomName;

    private String note;

    private Boolean isActive = true;

    @OneToMany(mappedBy = "classRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "scheduleRoom")
    private List<ClassSchedule> classSchedules;

	// Constructor nhận tham số Long cho ID
	public ClassRoom(Long id) {
		this.id = id;
	}

    public Boolean isActive(){
        return isActive;
    }
}