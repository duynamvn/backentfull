package com.project.backend_api.mapper;

import com.project.backend_api.dto.DegreeDTO;
import com.project.backend_api.dto.EmployeeDTO;
import com.project.backend_api.model.Degree;
import com.project.backend_api.model.Employee;

public class EmployeeMapper {

	public static EmployeeDTO toDto(Employee employee) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setId(employee.getId());
		dto.setFullName(employee.getFullName());
		dto.setDateOfBirth(employee.getDateOfBirth());
		dto.setGender(employee.getGender());
		dto.setNationalID(employee.getNationalID());
		dto.setEmail(employee.getEmail());
		dto.setPhoneNumber(employee.getPhoneNumber());
		dto.setActivate(employee.getActivate());
		dto.setAddress(employee.getAddress());
		
		Degree degree = employee.getDegree();
		if (degree != null) {
			DegreeDTO degreeDTO = new DegreeDTO();
			degreeDTO.setId(degree.getId());
			degreeDTO.setNote(degree.getNote());
			degreeDTO.setDegreeName(degree.getDegreeName());
			dto.setDegree(degreeDTO);
		}
		
		return dto;
	}
}
