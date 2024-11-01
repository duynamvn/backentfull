package com.project.backend_api.service;

import java.util.List;
import java.util.Optional;

import com.project.backend_api.model.Student;

public interface IStudentService {

	List<Student> getAllStudent();
	Optional<Student> getStudentById(Long id);
	Student createStudent(Student student);
	Student updateStudent(Long id, Student student);
	void deleteStudent(Long id);
	
}
