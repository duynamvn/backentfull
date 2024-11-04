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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	private String fullName;


    private LocalDate dateOfBirth; // Ngày sinh


    private String address; // Địa chỉ


    private String phoneNumber; // Số điện thoại


    private String gender; // Giới tính (1: Nam, 0: Nữ)

	
    @Column(name = "national_id", unique = true)
    private String nationalID; // Số chứng minh nhân dân

    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email; // Email

    private Boolean activate; // Trạng thái kích hoạt (1: Kích hoạt, 0: Không kích hoạt)

    private String studentCode; // Mã sinh viên

	private String imageName;
    
    // Thiết lập mối quan hệ n:1 với CategoryStudent
    @ManyToOne
    @JoinColumn(name = "student_type_id")
    @JsonBackReference(value = "student_type")
    private StudentType studentType; // Danh mục sinh viên
    
    // Thiết lập mối quan hệ với RegisterCourse
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "student_tuition")
    private List<TuitionFee> tuitionFees;
    
    // Thêm mối quan hệ với Attendance
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "attendStudent")
    private List<Attendance> attendances; // Danh sách attendance của sinh viên


}
