package net.polite.devtest.controller;

import net.polite.devtest.entity.User;
import net.polite.devtest.exception.UserAlreadyExistsException;
import net.polite.devtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userservice")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) throws UserAlreadyExistsException {
        User newUser = userRepository.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

}
