package com.project.backend_api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend_api.dto.TuitionFeeDTO;
import com.project.backend_api.mapper.TuitionFeeMapper;
import com.project.backend_api.model.TuitionFee;
import com.project.backend_api.service.ITuitionFeeService;

@RestController
@RequestMapping("/api/tuition-fees")
public class TuitionFeeController {

    @Autowired
    private ITuitionFeeService iTuitionFeeService;

    @GetMapping
    public ResponseEntity<List<TuitionFeeDTO>> getAllTuitionFee(){
        List<TuitionFee> tuitionFees = iTuitionFeeService.getAllTuitionFee();
        List<TuitionFeeDTO> tuitionFeeDTO = tuitionFees.stream()
                .map(TuitionFeeMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tuitionFeeDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TuitionFeeDTO> getTuitionFeeGetById(@PathVariable Long id){
        Optional<TuitionFee> tuitionFee = iTuitionFeeService.getTuitionFeeById(id);
        return tuitionFee.map(TuitionFeeMapper::toDto)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

//    @PostMapping
//    public ResponseEntity<TuitionFee> createTuitionFee(@RequestBody TuitionFee tuitionFee){
//        TuitionFee createTuitionFee = iTuitionFeeService.createTuitionFee(tuitionFee);
//        return ResponseEntity.ok(createTuitionFee);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<TuitionFee> updateTuitionFee(@PathVariable Long id, @RequestBody TuitionFee tuitionFee){
        TuitionFee updateTuitionFee = iTuitionFeeService.updateTuitionFee(id, tuitionFee);
        return ResponseEntity.ok(updateTuitionFee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TuitionFee> deleteTuitionFee(@PathVariable Long id){
        iTuitionFeeService.deleteTuitionFee(id);
        return ResponseEntity.noContent().build();
    }

    @Valid
    @PostMapping()
    public ResponseEntity<?> addTuitionFee(@RequestBody TuitionFee tuitionFee, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<String> errorMassages = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMassages);
        }
        try {
            TuitionFee createTuitionFee = iTuitionFeeService.createTuitionFee(tuitionFee);
            return ResponseEntity.status(HttpStatus.CREATED).body(createTuitionFee);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<TuitionFee> deactivateTuitionFee(@PathVariable("id") Long id, @RequestBody TuitionFee tuitionFee){
        try {
            iTuitionFeeService.updateIsActiveStatus(id, tuitionFee.getIsActive());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @GetMapping("/active")
    public ResponseEntity<?> getAllActiveTuitionFee() {
        List<TuitionFee> activeTuitionFees = iTuitionFeeService.getAllTuitionFee();
        if (activeTuitionFees.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No active tuition fees");
        }
        List<TuitionFeeDTO> tuitionFeeDTOs = activeTuitionFees.stream()
                .map(TuitionFeeMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tuitionFeeDTOs);
    }
}
