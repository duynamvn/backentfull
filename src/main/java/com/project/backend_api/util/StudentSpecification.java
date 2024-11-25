package com.project.backend_api.util;

import com.project.backend_api.model.Student;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification {

    public static Specification<Student> hasFullName(String fullName) {
        return (root, query, criteriaBuilder) -> {
            if (fullName == null || fullName.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(root.get("fullName"), "%" + fullName + "%");
        };
    }

    public static Specification<Student> hasStudentCode(String studentCode) {
        return (root, query, criteriaBuilder) -> {
            if (studentCode == null || studentCode.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(root.get("studentCode"), "%" + studentCode + "%");
        };
    }

    public static Specification<Student> hasStudentTypeName(String studentTypeName) {
        return (root, query, criteriaBuilder) -> {
            if (studentTypeName == null || studentTypeName.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(root.get("studentType").get("studentTypeName"), "%" + studentTypeName + "%");
        };
    }
}
