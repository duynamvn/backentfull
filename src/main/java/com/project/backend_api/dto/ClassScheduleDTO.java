package com.project.backend_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassScheduleDTO {

    private Long id;
    private String classDate;
    private Integer startTime;
    private Integer endTime;
    private ClassRoomDTO classRoom;
    private CourseDTO course;

}
