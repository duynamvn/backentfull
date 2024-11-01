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

import com.project.backend_api.model.Sessions;
import com.project.backend_api.service.ISessionService;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    @Autowired
    private ISessionService iSessionService;

    @GetMapping
    public List<Sessions> getAllSession(){
        return iSessionService.getAllSessions();
    }

    @GetMapping("/{id}")
    public Sessions getSessionById(@PathVariable Long id){
        return iSessionService.getSessionById(id);
    }

    @PostMapping
    public ResponseEntity<Sessions> createSession(@RequestBody Sessions session){
        Sessions createSession = iSessionService.saveSession(session);
        return ResponseEntity.ok(createSession);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sessions> updateSession(@PathVariable Long id, @RequestBody Sessions session){
        Sessions updateSession = iSessionService.updateSession(id, session);
        updateSession.setSessionName(session.getSessionName());
        updateSession.setTimeSlot(session.getTimeSlot());
        updateSession.setCourses(session.getCourses());
        return ResponseEntity.ok(updateSession);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Sessions> deleteSession(@PathVariable Long id){
        iSessionService.deleteSession(id);
        return ResponseEntity.noContent().build();
    }
}
