package com.project.backend_api.controller;

import java.util.List;

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
public class ClassRoomController {

    @Autowired
    private IClassRoomService iClassRoomService;

    @GetMapping
    public List<ClassRoom> getAllClassRoom(){
        return iClassRoomService.getAllClassRoom();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassRoom> getClassRoomById(@PathVariable Long id){
        ClassRoom classRoom = iClassRoomService.getClassRoomById(id);
        return ResponseEntity.ok(classRoom);
    }

    @PostMapping
    public ResponseEntity<ClassRoom> createClassRoom(@RequestBody ClassRoom classRoom){
        ClassRoom createClassRoom = iClassRoomService.saveClassRoom(classRoom);
        return ResponseEntity.ok(createClassRoom);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassRoom> updateClassRoom(@PathVariable Long id, @RequestBody ClassRoom classRoom){
        ClassRoom updateClassRoom = iClassRoomService.updateClassRoom(id, classRoom);
        return ResponseEntity.ok(updateClassRoom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClassRoom> deleteClassRoom(@PathVariable Long id){
        iClassRoomService.deleteClassRoom(id);
        return ResponseEntity.noContent().build();
    }
}
