package br.com.leopaschoarelli.authapi.service;

import br.com.leopaschoarelli.authapi.model.User;
import br.com.leopaschoarelli.authapi.repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{

    private UserRepo repo;

    public UserServiceImpl(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public User getByUsername(String username) {
        return null;
    }

}
