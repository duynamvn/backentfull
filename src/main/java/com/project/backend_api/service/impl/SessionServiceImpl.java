package com.project.backend_api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend_api.model.Course;
import com.project.backend_api.model.Sessions;
import com.project.backend_api.repository.CourseRepository;
import com.project.backend_api.repository.SessionRepository;
import com.project.backend_api.service.ISessionService;

@Service
public class SessionServiceImpl implements ISessionService{

	@Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Sessions> getAllSessions() {
        return sessionRepository.findAll();
    }

    @Override
    public Sessions getSessionById(Long id) {
        return sessionRepository.findById(id).orElseThrow();
    }

    @Override
    public Sessions saveSession(Sessions session) {
        List<Course> courses = session.getCourses().stream()
                .map(course -> courseRepository.findById(course.getId())
                        .orElse(course))
                .collect(Collectors.toList());
        session.setCourses(courses);
        return sessionRepository.save(session);
    }

    @Override
    public Sessions updateSession(Long id, Sessions session) {
        Sessions existingSession = sessionRepository.findById(id).orElseThrow();
        if (session != null){
            existingSession.setTimeSlot(session.getTimeSlot());
            existingSession.setSessionName(session.getSessionName());
            return sessionRepository.save(existingSession);
        }
        return null;
    }

    @Override
    public void deleteSession(Long id) {
        sessionRepository.deleteById(id);
    }

}
