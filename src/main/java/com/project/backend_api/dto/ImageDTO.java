package com.project.backend_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {

	private Long id;
	private String imageName;
	private String imageUrl;
	private StudentDTO student;
	private EmployeeDTO employee;
}
