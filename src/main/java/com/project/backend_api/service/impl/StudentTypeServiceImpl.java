package com.project.backend_api.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.project.backend_api.model.StudentType;
import com.project.backend_api.repository.StudentTypeRepository;
import com.project.backend_api.service.IStudentTypeService;

@Service
public class StudentTypeServiceImpl implements IStudentTypeService{

	@Autowired
	private StudentTypeRepository studentTypeRepository;
	
	
	
	@Override
	public List<StudentType> getAllStudentType() {
		// TODO Auto-generated method stub
		return studentTypeRepository.findAll();
	}

	@Override
    public StudentType createStudentType(StudentType studentType) {       
        return studentTypeRepository.save(studentType);
    }

	@Override
	public StudentType updateStudentType(Long id, StudentType studentType) {
		StudentType existingStudentType = studentTypeRepository.findById(id).orElseThrow();
		if (studentType != null) {
			existingStudentType.setStudentTypeName(studentType.getStudentTypeName());
			return studentTypeRepository.save(existingStudentType);
		}
		return null;
	}

	@Override
	public void deleteStudentType(Long id) {
		// TODO Auto-generated method stub
		studentTypeRepository.deleteById(id);
	}

	@Override
	public StudentType getStudentTypeById(Long id) {
		// TODO Auto-generated method stub
		return studentTypeRepository.findById(id).orElseThrow();
	}

}
