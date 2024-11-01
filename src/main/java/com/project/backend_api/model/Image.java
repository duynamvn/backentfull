package com.project.backend_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "image")
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "image_name")
	private String imageName;

	@Column(name = "image_url")
	private String imageUrl;

	// Thiết lập mối quan hệ n:1 với Student
    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference(value = "student_images")
    private Student student; // Sinh viên sở hữu hình ảnh này
    
    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference(value = "emp_images")
    private Employee employee;
}
