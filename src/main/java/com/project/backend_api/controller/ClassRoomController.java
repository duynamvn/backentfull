package com.project.backend_api.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend_api.model.ClassRoom;
import com.project.backend_api.service.IClassRoomService;

@RestController
@RequestMapping("/api/class-rooms")
@Slf4j
public class ClassRoomController {

    @Autowired
    private IClassRoomService iClassRoomService;

    @GetMapping
    public List<ClassRoom> getAllClassRoom(){
        log.info("Fetching all classrooms");
        return iClassRoomService.getAllClassRoom();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassRoom> getClassRoomById(@PathVariable Long id){
        log.info("Fetching classroom with id {}", id);
        ClassRoom classRoom = iClassRoomService.getClassRoomById(id);
        if (classRoom == null) {
            log.warn("Classroom with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(classRoom);
    }

    @PostMapping
    public ResponseEntity<ClassRoom> createClassRoom(@RequestBody ClassRoom classRoom){
        log.info("Creating classroom {}", classRoom);
        ClassRoom createClassRoom = iClassRoomService.saveClassRoom(classRoom);
        return ResponseEntity.ok(createClassRoom);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassRoom> updateClassRoom(@PathVariable Long id, @RequestBody ClassRoom classRoom){
        log.info("Updating classroom with id {}, new details: {}", id, classRoom);
        ClassRoom updateClassRoom = iClassRoomService.updateClassRoom(id, classRoom);
        if (updateClassRoom == null) {
            log.warn("Classroom with id {} not found for update", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateClassRoom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClassRoom> deleteClassRoom(@PathVariable Long id){
        log.info("Deleting classroom with id {}", id);
        iClassRoomService.deleteClassRoom(id);
        return ResponseEntity.noContent().build();
    }
}
