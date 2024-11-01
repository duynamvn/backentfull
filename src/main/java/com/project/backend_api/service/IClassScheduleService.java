package com.project.backend_api.service;

import java.util.List;
import java.util.Optional;

import com.project.backend_api.model.ClassSchedule;

public interface IClassScheduleService {

	List<ClassSchedule> getAllClassSchedule();
    Optional<ClassSchedule> getClassScheduleById(Long id);
    ClassSchedule saveClassSchedule(ClassSchedule classSchedule);
    ClassSchedule updateClassSchedule(Long id, ClassSchedule classSchedule); // Thêm phương thức update
    void deleteClassSchedule(Long id);
}
