package com.project.backend_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDateDTO {

    private Long id;
    private String courseName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long duration; // Lưu số ngày giữa startDate và endDate

    public CourseDateDTO(Long id, String courseName, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.courseName = courseName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = java.time.temporal.ChronoUnit.DAYS.between(this.startDate, this.endDate);
    }
}
