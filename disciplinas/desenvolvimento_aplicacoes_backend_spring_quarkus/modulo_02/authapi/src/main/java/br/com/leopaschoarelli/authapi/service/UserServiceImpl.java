package br.com.leopaschoarelli.authapi.service;

import br.com.leopaschoarelli.authapi.model.User;
import br.com.leopaschoarelli.authapi.repo.UserRepo;
import br.com.leopaschoarelli.authapi.security.MyToken;
import br.com.leopaschoarelli.authapi.security.TokenUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{

    private UserRepo repo;

    public UserServiceImpl(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public User addUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return null;
    }

    @Override
    public MyToken userLogin(User user) {
        User storedUser = repo.findByUsername(user.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(user.getPassword(), storedUser.getPassword())){
            return TokenUtil.enconde(storedUser);
        }
        throw new RuntimeException("Unauthorized User");
    }

}
