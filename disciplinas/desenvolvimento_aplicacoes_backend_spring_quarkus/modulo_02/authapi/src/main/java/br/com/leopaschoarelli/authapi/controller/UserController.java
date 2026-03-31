package br.com.leopaschoarelli.authapi.controller;

import br.com.leopaschoarelli.authapi.model.User;
import br.com.leopaschoarelli.authapi.security.MyToken;
import br.com.leopaschoarelli.authapi.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user){
        return ResponseEntity.status(201).body(service.addUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<MyToken> loging(@RequestBody User user) {
        return ResponseEntity.ok(service.userLogin(user));
    }

}
