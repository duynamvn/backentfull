package com.project.backend_api.service;

import java.util.List;

import com.project.backend_api.model.ClassRoom;

public interface IClassRoomService {

	List<ClassRoom> getAllClassRoom();
    ClassRoom getClassRoomById(Long id);
    ClassRoom saveClassRoom(ClassRoom classRoom);
    ClassRoom updateClassRoom(Long id, ClassRoom classRoom);
    void deleteClassRoom(Long id);
}
