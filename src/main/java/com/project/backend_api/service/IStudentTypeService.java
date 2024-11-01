package com.project.backend_api.service;

import java.util.List;

import com.project.backend_api.model.StudentType;

public interface IStudentTypeService {

	List<StudentType> getAllStudentType();
	StudentType getStudentTypeById(Long id);
	StudentType createStudentType(StudentType studentType);
	StudentType updateStudentType(Long id, StudentType studentType);
	void deleteStudentType(Long id);
}
