package com.project.backend_api.dto;

import java.time.LocalDate;

import com.project.backend_api.model.EmployeeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
	
    private Long id;
    private String fullName;
    private LocalDate dateOfBirth;
    private Boolean gender;
    private String nationalID;
    private String email;
    private String phoneNumber;
    private Boolean activate;
    private String address;
    private EmployeeType employeeType;
    private DegreeDTO degree;
    private SpecializationDTO specialization;
	
}
