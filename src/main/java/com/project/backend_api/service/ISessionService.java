package com.project.backend_api.service;

import java.util.List;

import com.project.backend_api.model.Sessions;

public interface ISessionService {

	List<Sessions> getAllSessions();
    Sessions getSessionById(Long id);
    Sessions saveSession(Sessions session);
    Sessions updateSession(Long id, Sessions session);
    void deleteSession(Long id);
}
