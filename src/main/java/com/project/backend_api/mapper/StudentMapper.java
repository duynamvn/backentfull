package com.project.backend_api.mapper;

import java.awt.Image;

import com.project.backend_api.dto.ImageDTO;
import com.project.backend_api.dto.StudentDTO;
import com.project.backend_api.dto.StudentTypeDTO;
import com.project.backend_api.model.Student;
import com.project.backend_api.model.StudentType;

public class StudentMapper {

	public static StudentDTO toDTO(Student student) {
		StudentDTO dto = new StudentDTO();
		dto.setId(student.getId());
		dto.setFullName(student.getFullName());
		dto.setDateOfBirth(student.getDateOfBirth());
		dto.setAddress(student.getAddress());
		dto.setPhoneNumber(student.getPhoneNumber());
		dto.setGender(student.getGender());
		dto.setNationalID(student.getNationalID());
		dto.setEmail(student.getEmail());
		dto.setActivate(student.getActivate());
		dto.setStudentCode(student.getStudentCode());
		
		StudentType studentType = student.getStudentType();
		if (studentType != null) {
			StudentTypeDTO studentTypeDTO = new StudentTypeDTO();
			studentTypeDTO.setId(studentType.getId());
			studentTypeDTO.setStudentTypeName(studentType.getStudentTypeName());
			dto.setStudentType(studentTypeDTO);
		}
		
		
		return dto;
	}
}
