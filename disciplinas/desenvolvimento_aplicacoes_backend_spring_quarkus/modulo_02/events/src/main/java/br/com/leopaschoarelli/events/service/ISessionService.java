package br.com.leopaschoarelli.events.service;

import br.com.leopaschoarelli.events.model.Session;

import java.util.List;

public interface ISessionService {

    public Session addSession(Session session);
    public Session getSessionById(Integer id);
    public List<Session> getAllSessions();

}
