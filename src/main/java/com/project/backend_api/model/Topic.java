package com.project.backend_api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "topic_code")
    private String topicCode;
    
    @Column(name = "topic_name")
    private String topicName;
    
    @Column(name = "theory_hours")
    private Integer theoryHours;
    
    @Column(name = "practical_hours")
    private Integer practicalHours;
    
    @Column(name = "activate")
    private Boolean activate;
    
    @Column(name = "original_price")
    private  double originalPrice;
    
    @Column(name = "promotional_price")
    private double PromotionalPrice;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "teachTopic")
    private List<TeachingAbility> teachingAbilities;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "topicCourse")
    private List<Course> courses;

}
