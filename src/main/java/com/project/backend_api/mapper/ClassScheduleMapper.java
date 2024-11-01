package com.project.backend_api.mapper;

import java.time.format.DateTimeFormatter;

import com.project.backend_api.dto.ClassRoomDTO;
import com.project.backend_api.dto.ClassScheduleDTO;
import com.project.backend_api.dto.CourseDTO;
import com.project.backend_api.dto.SessionDTO;
import com.project.backend_api.dto.TopicDTO;
import com.project.backend_api.model.ClassRoom;
import com.project.backend_api.model.ClassSchedule;
import com.project.backend_api.model.Course;

public class ClassScheduleMapper {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static ClassScheduleDTO toDto(ClassSchedule classSchedule){
        ClassScheduleDTO dto = new ClassScheduleDTO();
        dto.setId(classSchedule.getId());
        dto.setClassDate(classSchedule.getClassDate().format(DATE_FORMATTER));  // Chuyển LocalDate sang String

        // Chuyển LocalTime sang số phút hoặc String nếu cần
        dto.setStartTime(classSchedule.getStartTime().getHour() * 60 + classSchedule.getStartTime().getMinute());
        dto.setEndTime(classSchedule.getEndTime().getHour() * 60 + classSchedule.getEndTime().getMinute());

        ClassRoom classRoom = classSchedule.getClassRoom();
        if (classRoom != null){
            ClassRoomDTO classRoomDTO = new ClassRoomDTO();
            classRoomDTO.setId(classRoom.getId());
            classRoomDTO.setRoomName(classRoom.getRoomName());
            classRoomDTO.setNote(classRoom.getNote());
            dto.setClassRoom(classRoomDTO);
        }

        Course course = classSchedule.getCourse();
        if (course != null){
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(course.getId());
            courseDTO.setCourseCode(course.getCourseCode());
            courseDTO.setCourseName(course.getCourseName());
            courseDTO.setStartDate(course.getStartDate());
            courseDTO.setEndDate(course.getEndDate());
            courseDTO.setTotalStudent(course.getTotalStudent());
            courseDTO.setActivate(course.getActivate());
            dto.setCourse(courseDTO);

            if (course.getSession() != null){
                SessionDTO sessionDTO = new SessionDTO();
                sessionDTO.setId(course.getId());
                sessionDTO.setSessionName(course.getSession().getSessionName());
                sessionDTO.setTimeSlot(course.getSession().getTimeSlot());
                courseDTO.setSession(sessionDTO);
            }
            
            if (course.getTopic() != null) {
            	TopicDTO topicDTO = new TopicDTO();
            	topicDTO.setId(course.getId());
            	topicDTO.setTopicCode(course.getTopic().getTopicCode());
            	topicDTO.setTopicName(course.getTopic().getTopicName());
            	topicDTO.setActivate(course.getTopic().getActivate());
            }
            
            dto.setCourse(courseDTO);

        }

        return dto;
    }

}
