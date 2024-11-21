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

	Long getLatestStudentId();
	Optional<Student> getStudentByStudentCode(String studentCode);
	List<Student> getStudentByFullName(String fullName);

	boolean existsByEmail(String email);
	boolean existsByNationalID(String nationalId);

	// chỉ updat isActive
	void updateIsActiveStatus(Long id, Boolean isActive);
}
