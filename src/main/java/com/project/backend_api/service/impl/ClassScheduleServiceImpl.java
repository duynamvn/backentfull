package com.project.backend_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend_api.model.ClassSchedule;
import com.project.backend_api.repository.ClassScheduleRepository;
import com.project.backend_api.service.IClassScheduleService;

@Service
public class ClassScheduleServiceImpl implements IClassScheduleService{

	@Autowired
	private ClassScheduleRepository classScheduleRepository;
	
	@Override
    public List<ClassSchedule> getAllClassSchedule() {
        return classScheduleRepository.findAll();
    }

    @Override
    public Optional<ClassSchedule> getClassScheduleById(Long id) {
        return classScheduleRepository.findById(id);
    }

    @Override
    public ClassSchedule saveClassSchedule(ClassSchedule classSchedule) {
        return classScheduleRepository.save(classSchedule);
    }

    @Override
    public ClassSchedule updateClassSchedule(Long id, ClassSchedule classSchedule) {
        ClassSchedule existingClassSchedule = classScheduleRepository.findById(id).orElseThrow(null);
        if (existingClassSchedule != null){
            existingClassSchedule.setClassDate(classSchedule.getClassDate());
            existingClassSchedule.setStartTime(classSchedule.getStartTime());
            existingClassSchedule.setEndTime(classSchedule.getEndTime());
            existingClassSchedule.setClassRoom(classSchedule.getClassRoom());
            existingClassSchedule.setClassRoom(classSchedule.getClassRoom());
            existingClassSchedule.setCourse(classSchedule.getCourse());
            existingClassSchedule.setClassRoom(classSchedule.getClassRoom());
            return classScheduleRepository.save(existingClassSchedule);
        }
        return null;
    }

    @Override
    public void deleteClassSchedule(Long id) {
        classScheduleRepository.deleteById(id);
    }

}
