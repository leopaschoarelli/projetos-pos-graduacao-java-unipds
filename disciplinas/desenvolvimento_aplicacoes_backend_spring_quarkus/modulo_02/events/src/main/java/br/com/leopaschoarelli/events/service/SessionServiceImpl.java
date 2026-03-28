package br.com.leopaschoarelli.events.service;

import br.com.leopaschoarelli.events.exception.NotFoundException;
import br.com.leopaschoarelli.events.model.Session;
import br.com.leopaschoarelli.events.repository.SessionRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements ISessionService {

    private SessionRepo repo;

    public SessionServiceImpl(SessionRepo repo) {
        this.repo = repo;
    }

    @Override
    public Session addSession(Session session) {
        return repo.save(session);
    }

    @Override
    public Session getSessionById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Session not found: " + id));
    }

    @Override
    public List<Session> getAllSessions() {
        return repo.findAll();
    }

}
