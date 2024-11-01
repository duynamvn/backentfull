package com.project.backend_api.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

	private Long id;

	private String fullName;

    private LocalDate dateOfBirth; // Ngày sinh

    private String address; // Địa chỉ

    private String phoneNumber; // Số điện thoại

    private Boolean gender; // Giới tính (1: Nam, 0: Nữ)

    private String nationalID; // Số chứng minh nhân dân

    private String email; // Email

    private Boolean activate; // Trạng thái kích hoạt (1: Kích hoạt, 0: Không kích hoạt)

    private String studentCode; // Mã sinh viên
    
    private StudentTypeDTO studentType;

}
