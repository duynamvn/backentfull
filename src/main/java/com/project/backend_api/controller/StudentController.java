package com.project.backend_api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend_api.dto.StudentDTO;
import com.project.backend_api.mapper.StudentMapper;
import com.project.backend_api.model.Student;
import com.project.backend_api.service.IStudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private IStudentService iStudentService;
	
	@GetMapping
	public ResponseEntity<List<StudentDTO>>  getAllStudent(){
		List<Student> students = iStudentService.getAllStudent();
		List<StudentDTO> studentDTO = students.stream()
				.map(StudentMapper::toDTO)
				.collect(Collectors.toList());
		return ResponseEntity.ok(studentDTO);
	}
	
	 
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
		Optional<Student> student = iStudentService.getStudentById(id);
		return student.map(StudentMapper::toDTO)
				.map(ResponseEntity::ok).orElseGet(() -> 
				ResponseEntity.noContent().build());
	}
	
	@PostMapping
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		Student createStudent = iStudentService.createStudent(student);
		return ResponseEntity.ok(createStudent);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
		Student updateStudent = iStudentService.updateStudent(id, student);
		return ResponseEntity.ok(updateStudent);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
		iStudentService.deleteStudent(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
