package br.com.leopaschoarelli.authapi.repo;

import java.util.Optional;

import br.com.leopaschoarelli.authapi.model.User;
import org.springframework.data.repository.ListCrudRepository;


public interface UserRepo extends ListCrudRepository<User, Integer>{

    public Optional<User> findByUsername(String username);

}
