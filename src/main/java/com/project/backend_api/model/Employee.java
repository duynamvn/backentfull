package com.project.backend_api.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName; // Họ và tên

    private LocalDate dateOfBirth; // Ngày sinh

    private String gender; // Giới tính (1: Nam, 0: Nữ)

    @Column(name = "national_id", unique = true)
    @Size(min = 12, max = 12, message = "NationalID phải có đủ 12 chữ số")
    @Pattern(regexp = "\\d{12}", message = "NationalID chỉ được chứa các chữ số")
    private String nationalID; // Số chứng minh nhân dân

    @Column(unique = true)
    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 10, max = 10, message = "PhoneNumber phải có đủ 10 chữ số")
    @Pattern(regexp = "^(03|05|07|08|09)\\d{8}$", message = "PhoneNumber phải bắt đầu bằng 03|05|07|08|09 và có đủ 10 chữ số")
    private String phoneNumber;

    private Boolean activate;

    private String address;

    private String imageName;
    
    // Mối quan hệ 1:N với TeachingAssignment
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "emp_teaching")
    private List<TeachingAssignment> teachingAssignments; // Danh sách phân công giảng dạy

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "empTeach")
    private List<TeachingAbility> teachingAbilities;

    
    @ManyToOne
    @JoinColumn(name = "degree_id")
    @JsonBackReference(value = "emp_degree")
    private Degree degree; // Trình độ của nhân viên
    
    // Mối quan hệ n:1 với EmployeeType
    @ManyToOne
    @JoinColumn(name = "employee_type_id")
    @JsonBackReference(value = "emp_type")
    private EmployeeType employeeType; // Kiểu nhân viên
    
    @ManyToOne
    @JoinColumn(name = "specialization_id")
    @JsonBackReference(value = "spec_emp")
    private Specialization specialization;

    public Employee(Long id){
        this.id = id;
    }
}
