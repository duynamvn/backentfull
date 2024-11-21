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
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseCode;

    private String courseName;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer totalStudent;

    private Boolean activate ;

    private Boolean isActive = true;
    // Mối quan hệ 1:N với TeachingAssignment
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "course_teaching") // Sử dụng cùng giá trị với TeachingAssignment
    private List<TeachingAssignment> teachingAssignments; // Danh sách phân công giảng dạy

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "session_id")// This prevents infinite recursion
    @JsonBackReference(value = "sessionCourse") // Thay đổi thành JsonManagedReference
    private Sessions session;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Mối quan hệ n:1
    @JsonManagedReference(value = "scheduleCourse")
    private List<ClassSchedule> classSchedules;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id")
    @JsonBackReference(value = "topicCourse")
    private Topic topic;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "tuitionCourse")
    private List<TuitionFee> tuitionFees;

    public Course(Long id) {
        this.id = id;
    }
    public Boolean isActive() {
        return isActive;
    }

}
