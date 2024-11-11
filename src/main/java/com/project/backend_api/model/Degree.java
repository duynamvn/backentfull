package com.project.backend_api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "degree")
public class Degree {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String degreeName;

	private String note;
	
	@OneToMany(mappedBy = "degree", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Mối quan hệ 1:N với Employee
	@JsonManagedReference(value = "emp_degree")
	private List<Employee> employees; // Danh sách nhân viên

	public Degree(Long id){
		this.id = id;
	}
}
