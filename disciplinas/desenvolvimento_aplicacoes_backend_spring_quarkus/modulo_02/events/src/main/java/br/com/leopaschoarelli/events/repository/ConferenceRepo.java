package br.com.leopaschoarelli.events.repository;

import br.com.leopaschoarelli.events.model.Conference;
import org.springframework.data.repository.ListCrudRepository;

public interface ConferenceRepo extends ListCrudRepository<Conference, Integer> {

}
