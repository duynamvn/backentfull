package com.project.backend_api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend_api.model.ClassRoom;
import com.project.backend_api.repository.ClassRoomRepository;
import com.project.backend_api.service.IClassRoomService;

@Service
public class ClassRoomServiceImpl implements IClassRoomService{

	@Autowired
	private ClassRoomRepository classRoomRepository;
	
	@Override
    public List<ClassRoom> getAllClassRoom() {
        return classRoomRepository.findAll();
    }

    @Override
    public ClassRoom getClassRoomById(Long id) {
        return classRoomRepository.findById(id).orElse(null);
    }

    @Override
    public ClassRoom saveClassRoom(ClassRoom classRoom) {
        return classRoomRepository.save(classRoom);
    }

    @Override
    public ClassRoom updateClassRoom(Long id, ClassRoom classRoom) {
        ClassRoom existingClassRoom = classRoomRepository.findById(id).orElseThrow(null);
        if (existingClassRoom != null){
            existingClassRoom.setRoomName(classRoom.getRoomName());
            existingClassRoom.setNote(classRoom.getNote());
            return classRoomRepository.save(classRoom);
        }
        return null;
    }

    @Override
    public void deleteClassRoom(Long id) {
        classRoomRepository.deleteById(id);
    }

}
