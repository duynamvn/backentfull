package com.project.backend_api.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
	
    private Long id;
    private String courseCode;
    private String courseName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer totalStudent;
    private Boolean activate;
    private SessionDTO session;
    private TopicDTO topics;
    
    
}
