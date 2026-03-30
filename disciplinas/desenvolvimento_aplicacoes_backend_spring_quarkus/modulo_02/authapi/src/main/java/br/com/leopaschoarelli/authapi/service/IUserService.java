package br.com.leopaschoarelli.authapi.service;

import br.com.leopaschoarelli.authapi.model.User;

public interface IUserService {

    public User addUser(User user);
    public User getByUsername(String username);

}