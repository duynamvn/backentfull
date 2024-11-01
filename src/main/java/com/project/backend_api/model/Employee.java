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

    @Column(name = "full_name")
    private String fullName; // Họ và tên

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth; // Ngày sinh

    @Column(name = "gender")
    private Boolean gender; // Giới tính (1: Nam, 0: Nữ)

    @Column(name = "national_id")
    private String nationalID; // Số chứng minh nhân dân

    @Column(name = "email")
    private String email; // Email

    @Column(name = "phone_number")
    private String phoneNumber; // Số điện thoại

    @Column(name = "activate")
    private Boolean activate; // Trạng thái kích hoạt (1: Kích hoạt, 0: Không kích hoạt)

    @Column(name = "address")
    private String address;
    
    // Mối quan hệ 1:N với TeachingAssignment
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "emp_teaching")
    private List<TeachingAssignment> teachingAssignments; // Danh sách phân công giảng dạy

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "empTeach")
    private List<TeachingAbility> teachingAbilities;
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "emp_images")
    private List<Image> images;
    
    @ManyToOne
    @JoinColumn(name = "degree_id")
    @JsonBackReference(value = "emp_degree")
    private Degree degree; // Trình độ của nhân viên
    
    // Mối quan hệ n:1 với EmployeeType
    @ManyToOne
    @JoinColumn(name = "employee_type_id", nullable = false)
    @JsonBackReference(value = "emp_type")
    private EmployeeType employeeType; // Kiểu nhân viên
    
    @ManyToOne
    @JoinColumn(name = "specialization_id")
    @JsonBackReference(value = "spec_emp")
    private Specialization specialization;

}
