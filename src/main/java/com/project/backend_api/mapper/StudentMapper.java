package com.project.backend_api.mapper;

import com.project.backend_api.dto.StudentDTO;
import com.project.backend_api.dto.StudentTypeDTO;
import com.project.backend_api.model.Student;
import com.project.backend_api.model.StudentType;
import com.project.backend_api.model.TuitionFee;

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
		dto.setImageName(student.getImageName());
		dto.setIsActive(student.getIsActive());
		dto.setIsActive(student.getIsActive());
		
		StudentType studentType = student.getStudentType();
		if (studentType != null) {
			StudentTypeDTO studentTypeDTO = new StudentTypeDTO();
			studentTypeDTO.setId(studentType.getId());
			studentTypeDTO.setStudentTypeName(studentType.getStudentTypeName());
			dto.setStudentType(studentTypeDTO);
		}
		if (student.getTuitionFees() != null && !student.getTuitionFees().isEmpty()) {
			TuitionFee tuitionFee = student.getTuitionFees().get(0);
			dto.setCollectedMoney(tuitionFee.getCollectedMoney());
		}
		
		return dto;
	}
}
