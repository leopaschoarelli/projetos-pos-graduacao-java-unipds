package br.com.leopaschoarelli.events.service;

import br.com.leopaschoarelli.events.model.Session;
import br.com.leopaschoarelli.events.model.Subscription;
import br.com.leopaschoarelli.events.model.User;

import java.util.List;

public interface ISubscriptionService {

    public Subscription addSubscription(Subscription subscription);
    public List<Subscription> getAllByUser(User user);
    public List<Subscription> getAllBySession(Session session);

}
