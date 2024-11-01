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
@Table(name = "employeeType")
public class EmployeeType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String employeeTypeName;
	
	// Thiết lập mối quan hệ 1:N với Employee
    @OneToMany(mappedBy = "employeeType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "emp_type")
    private List<Employee> employees;
}
