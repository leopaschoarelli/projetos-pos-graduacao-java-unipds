package br.com.leopaschoarelli.events.repository;

import br.com.leopaschoarelli.events.model.Session;
import br.com.leopaschoarelli.events.model.Subscription;
import br.com.leopaschoarelli.events.model.SubscriptionID;
import br.com.leopaschoarelli.events.model.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface SubscriptionRepo extends ListCrudRepository<Subscription, SubscriptionID> {

    public List<Subscription> findByIdUser(User user);
    public List<Subscription> findByIdSession(Session session);

}
