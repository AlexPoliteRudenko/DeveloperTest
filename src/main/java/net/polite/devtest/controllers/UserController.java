package net.polite.devtest.controllers;

import net.polite.devtest.exceptions.UserAlreadyExistsException;
import net.polite.devtest.repository.UserRepository;
import net.polite.devtest.repository.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        userRepository.createUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userRepository.getAll(), HttpStatus.OK);
    }

}
