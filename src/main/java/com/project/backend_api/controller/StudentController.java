package com.project.backend_api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	private String generateStudentCode(String fullName) {

		if (fullName == null || fullName.trim().isEmpty()) {
			throw new IllegalArgumentException("Full name cannot be null or empty");
		}
		String[] words = fullName.split(" ");
		StringBuilder sb = new StringBuilder();
		for (String word : words) {
			sb.append(word.charAt(0));
		}
		Long latestId = iStudentService.getLatestStudentId();
		int newId = latestId != null ? (int) (latestId + 1) : 1;

		return sb.toString().toUpperCase() + String.format("%02d", newId);
	}

	@PostMapping
	public ResponseEntity<?> createStudent(@RequestBody Student student) {
		try {
			String generatedStudentCode = generateStudentCode(student.getFullName());
			student.setStudentCode(generatedStudentCode);
			Student existingStudent = iStudentService.createStudent(student);
			return ResponseEntity.status(HttpStatus.CREATED).body(existingStudent);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student student) {
		try {
			Student existingStudent = iStudentService.getStudentById(id)
					.orElseThrow(() -> new RuntimeException("Student with id " + id + " not found"));

			if (!existingStudent.getFullName().equals(student.getFullName())) {
				String generatedStudentCode = generateStudentCode(student.getFullName());
				student.setStudentCode(generatedStudentCode);
			} else {
				student.setStudentCode(existingStudent.getStudentCode());
			}
			student.setId(existingStudent.getId());
			Student updatedStudent = iStudentService.updateStudent(id, student);
			return ResponseEntity.ok(updatedStudent);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
		iStudentService.deleteStudent(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/code/{studentCode}")
	public ResponseEntity<StudentDTO> getStudentByStudentCode(@PathVariable String studentCode) {
		Optional<Student> student = iStudentService.getStudentByStudentCode(studentCode);
		return student.map(StudentMapper::toDTO)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.noContent().build());
	}

	@GetMapping("/name")
	public ResponseEntity<List<StudentDTO>> getStudentByFullName(@RequestParam(name = "fullName") String fullName) {
		List<Student> student = iStudentService.getStudentByFullName(fullName);
		List<StudentDTO> studentDTO = student.stream()
				.map(StudentMapper::toDTO)
				.collect(Collectors.toList());
		return ResponseEntity.ok(studentDTO);
	}
	
}
