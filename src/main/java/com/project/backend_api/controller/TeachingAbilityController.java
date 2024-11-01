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

import com.project.backend_api.dto.TeachingAbilityDTO;
import com.project.backend_api.mapper.TeachingAbilityMapper;
import com.project.backend_api.model.TeachingAbility;
import com.project.backend_api.service.ITeachingAbilityService;

@RestController
@RequestMapping("/api/teaching-abilities")
public class TeachingAbilityController {

    @Autowired
    private ITeachingAbilityService iTeachingAbilityService;

    @GetMapping
    public ResponseEntity<List<TeachingAbilityDTO>> getAllTeachingAbility(){
        List<TeachingAbility> teachingAbilities = iTeachingAbilityService.getAllTeachingAbility();
        List<TeachingAbilityDTO> teachingAbilityDTO = teachingAbilities.stream()
                .map(TeachingAbilityMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(teachingAbilityDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeachingAbilityDTO> getTeachingAbilityById(@PathVariable Long id){
        Optional<TeachingAbility> teachingAbility = iTeachingAbilityService.getTeachingAbilityById(id);
        return teachingAbility.map(TeachingAbilityMapper::toDto)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<TeachingAbility> createTeachingAbility(@RequestBody TeachingAbility teachingAbility){
        TeachingAbility createTeachingAbility = iTeachingAbilityService.saveTeachingAbility(teachingAbility);
        return ResponseEntity.ok(createTeachingAbility);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeachingAbility> updateTeachingAbility(@PathVariable Long id, @RequestBody TeachingAbility teachingAbility){
        TeachingAbility updateTeachingAbility = iTeachingAbilityService.updateTeachingAbility(id, teachingAbility);
        return ResponseEntity.ok(updateTeachingAbility);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TeachingAbility> deleteTeachingAbility(@PathVariable Long id){
        iTeachingAbilityService.deleteTeachingAbility(id);
        return ResponseEntity.noContent().build();
    }
}