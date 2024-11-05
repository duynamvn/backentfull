package com.project.backend_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.backend_api.model.Student;
import com.project.backend_api.repository.StudentRepository;
import com.project.backend_api.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService{

	@Autowired
	private StudentRepository studentRepository;
	
	
	@Override
	public List<Student> getAllStudent() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	@Override
	public Optional<Student> getStudentById(Long id) {
		// TODO Auto-generated method stub
		return studentRepository.findById(id);
	}

	@Override
	public Student createStudent(Student student) {
		// TODO Auto-generated method stub
		return studentRepository.save(student);
	}

	@Override
	public Student updateStudent(Long id, Student student) {
		// TODO Auto-generated method stub
		Student existingStudent = studentRepository.findById(id).orElseThrow();

		// Kiểm tra nếu email mới đã tồn tại cho một học viên khác
		Optional<Student> studentWithSameEmail = studentRepository.findByEmail(student.getEmail());
		if (studentWithSameEmail.isPresent() && !studentWithSameEmail.get().getId().equals(id)) {
			throw new IllegalArgumentException("Email already exists");
		}

		Optional<Student> studentWithSamNationalId = studentRepository.findByNationalID(student.getNationalID());
		if (studentWithSamNationalId.isPresent() && !studentWithSamNationalId.get().getId().equals(id)) {
			throw new IllegalArgumentException("National ID already exists");
		}

		existingStudent.setFullName(student.getFullName());
		existingStudent.setDateOfBirth(student.getDateOfBirth());
		existingStudent.setAddress(student.getAddress());
		existingStudent.setPhoneNumber(student.getPhoneNumber());
		existingStudent.setGender(student.getGender());
		existingStudent.setNationalID(student.getNationalID());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setActivate(student.getActivate());
		existingStudent.setImageName(student.getImageName());

		existingStudent.setStudentCode(student.getStudentCode());
		existingStudent.setStudentType(student.getStudentType());
		return studentRepository.save(existingStudent);
	}

	@Override
	public void deleteStudent(Long id) {
		studentRepository.deleteById(id);
	}

	@Override
	public Optional<Student> getStudentByStudentCode(String studentCode) {
		return studentRepository.findByStudentCode(studentCode);
	}

	@Override
	public List<Student> getStudentByFullName(String fullName) {
		System.out.println("Searching for name containing: " + fullName);
		return studentRepository.findStudentByFullName(fullName);
	}
}
