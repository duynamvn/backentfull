package com.project.backend_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicDTO {

    private Long id;
    private String topicCode;
    private String topicName;
    private Integer theoryHours;
    private Integer practicalHours;
    private Boolean activate;
    private double originalPrice;
    private double PromotionalPrice;
    
	
}
