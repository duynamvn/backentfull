package com.project.backend_api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.project.backend_api.dto.ClassScheduleDTO;
import com.project.backend_api.mapper.ClassScheduleMapper;
import com.project.backend_api.model.ClassSchedule;
import com.project.backend_api.service.IClassScheduleService;

@RestController
@RequestMapping("/api/class-schedules")
public class ClassScheduleController {

    @Autowired
    private IClassScheduleService iClassScheduleService;

    @GetMapping
    public ResponseEntity<List<ClassScheduleDTO>> getAllClassSchedule(){
        List<ClassSchedule> classSchedules = iClassScheduleService.getAllClassSchedule();
        List<ClassScheduleDTO> classScheduleDTOS = classSchedules.stream()
                .map(ClassScheduleMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(classScheduleDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassScheduleDTO> getClassScheduleById(@PathVariable Long id){
        Optional<ClassSchedule> classSchedule = iClassScheduleService.getClassScheduleById(id);
        return classSchedule.map(ClassScheduleMapper::toDto)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<ClassSchedule> createClassSchedule(@RequestBody ClassSchedule classSchedule){
        ClassSchedule createClassSchedule = iClassScheduleService.saveClassSchedule(classSchedule);
        return ResponseEntity.ok(createClassSchedule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassSchedule> updateClassSchedule(@PathVariable Long id, @RequestBody ClassSchedule classSchedule){
        ClassSchedule updateClassSchedule = iClassScheduleService.updateClassSchedule(id, classSchedule);
        return ResponseEntity.ok(updateClassSchedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClassSchedule> deleteClassSchedule(@PathVariable Long id){
        iClassScheduleService.deleteClassSchedule(id);
        return ResponseEntity.noContent().build();
    }
}
