package com.project.backend_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentTypeDTO {

	private Long id;

	private String studentTypeName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStudentTypeName() {
		return studentTypeName;
	}

	public void setStudentTypeName(String studentTypeName) {
		this.studentTypeName = studentTypeName;
	}
	
}
