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
    private StudentDTO student;
    private Double originalPrice;
    private Double promotionalPrice;
}
