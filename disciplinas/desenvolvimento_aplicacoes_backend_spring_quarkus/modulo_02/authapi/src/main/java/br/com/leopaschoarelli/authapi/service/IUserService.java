package br.com.leopaschoarelli.authapi.service;

import br.com.leopaschoarelli.authapi.model.User;
import br.com.leopaschoarelli.authapi.security.MyToken;

public interface IUserService {

    public User addUser(User user);
    public User getByUsername(String username);
    public MyToken userLogin(User user);

}