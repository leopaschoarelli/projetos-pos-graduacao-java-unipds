package br.com.leopaschoarelli.events.service;

import br.com.leopaschoarelli.events.model.Conference;

import java.util.List;

public interface IConferenceService {

    public Conference addConference(Conference conference);
    public Conference getConferenceById(Integer id);
    public List<Conference> getAllConferences();

}
