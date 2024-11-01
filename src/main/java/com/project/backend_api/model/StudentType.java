package com.project.backend_api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "StudentType")
public class StudentType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "student_type_name")
	private String studentTypeName;
	
	// Thiết lập mối quan hệ 1:n với Student
	@OneToMany(mappedBy = "studentType", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value = "student_type")
	private List<Student> students;	// Danh sách sinh viên thuộc danh mục này

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStudentTypeName() {
		return studentTypeName;
	}

	public void setStudentTypeName(String studentTypeName) {
		this.studentTypeName = studentTypeName;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	
}