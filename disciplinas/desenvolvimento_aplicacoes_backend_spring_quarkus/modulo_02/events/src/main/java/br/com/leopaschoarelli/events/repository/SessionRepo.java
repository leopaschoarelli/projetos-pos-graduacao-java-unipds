package br.com.leopaschoarelli.events.repository;

import br.com.leopaschoarelli.events.model.Session;
import org.springframework.data.repository.ListCrudRepository;

public interface SessionRepo extends ListCrudRepository<Session, Integer> {

}
