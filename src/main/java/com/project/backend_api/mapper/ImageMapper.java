package com.project.backend_api.mapper;

import com.project.backend_api.dto.EmployeeDTO;
import com.project.backend_api.dto.ImageDTO;
import com.project.backend_api.dto.StudentDTO;
import com.project.backend_api.dto.StudentTypeDTO;
import com.project.backend_api.model.Employee;
import com.project.backend_api.model.Image;
import com.project.backend_api.model.Student;

public class ImageMapper {

	public static ImageDTO toDTO(Image image) {
		
		ImageDTO dto = new ImageDTO();
		dto.setId(image.getId());
		dto.setImageName(image.getImageName());
		dto.setImageUrl(image.getImageUrl());
		
		Student student = image.getStudent();
		if (student != null) {
			StudentDTO studentDTO = new StudentDTO();
			studentDTO.setId(student.getId());
			studentDTO.setFullName(student.getFullName());
			studentDTO.setDateOfBirth(student.getDateOfBirth());
			studentDTO.setAddress(student.getAddress());
			studentDTO.setPhoneNumber(student.getPhoneNumber());
			studentDTO.setGender(student.getGender());
			studentDTO.setNationalID(student.getNationalID());
			studentDTO.setEmail(student.getEmail());
			studentDTO.setActivate(student.getActivate());
			studentDTO.setStudentCode(student.getStudentCode());
			dto.setStudent(studentDTO);
			
			if (student.getStudentType() != null) {
				StudentTypeDTO studentTypeDTO = new StudentTypeDTO();
				studentTypeDTO.setId(student.getId());
				studentTypeDTO.setStudentTypeName(student.getStudentType().getStudentTypeName());
				studentDTO.setStudentType(studentTypeDTO);
			}
			dto.setStudent(studentDTO);
		}
		
		Employee employee = image.getEmployee();
		if (employee != null) {
			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setId(employee.getId());
			employeeDTO.setFullName(employee.getFullName());
			employeeDTO.setPhoneNumber(employee.getPhoneNumber());
			dto.setEmployee(employeeDTO);
		}
		
		
		return dto;
	}
}
