package com.project.backend_api.service.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend_api.model.ClassRoom;
import com.project.backend_api.repository.ClassRoomRepository;
import com.project.backend_api.service.IClassRoomService;

@Service
@Slf4j
public class ClassRoomServiceImpl implements IClassRoomService{

	@Autowired
	private ClassRoomRepository classRoomRepository;
	
	@Override
    public List<ClassRoom> getAllClassRoom() {
        log.info("Fetching all classrooms from the database");
        return classRoomRepository.findAll();
    }

    @Override
    public ClassRoom getClassRoomById(Long id) {
        log.info("Fetching classroom with id {} ", id);
        ClassRoom classRoom = classRoomRepository.findById(id).orElse(null);
        if (classRoom == null) {
            log.info("Classroom with id {} not found", id);
        }
        return classRoom;
    }

    @Override
    public ClassRoom saveClassRoom(ClassRoom classRoom) {
        log.info("Saving classroom {}", classRoom);
        return classRoomRepository.save(classRoom);
    }

    @Override
    public ClassRoom updateClassRoom(Long id, ClassRoom classRoom) {
        log.info("Updating classroom with id {} ", id);
        ClassRoom existingClassRoom = classRoomRepository.findById(id).orElseThrow(null);
        if (existingClassRoom != null){
            log.info("Found classroom with id {} for update, updating details", id);
            existingClassRoom.setRoomName(classRoom.getRoomName());
            existingClassRoom.setNote(classRoom.getNote());
            return classRoomRepository.save(classRoom);
        } else {
            log.warn("Classroom with id {} not found, cannot update", id);
            return null;
        }
    }

    @Override
    public void deleteClassRoom(Long id) {
        log.info("Deleting classroom with id {} ", id);
        if (classRoomRepository.existsById(id)) {
            classRoomRepository.deleteById(id);
            log.info("Classroom with id {} deleted successfully", id);
        } else {
            log.warn("Classroom with id {} not found, cannot delete", id);
        }
    }

}
