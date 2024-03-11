package com.maverick_shravan.User_Service.RestController;

import com.maverick_shravan.User_Service.Service.UserService;
import com.maverick_shravan.User_Service.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;


@RestController
public class UserRestController {

    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    private final UserService myUserService;

    @Autowired
    public UserRestController(UserService userService) {
        this.myUserService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        logger.info("Creating user: {}", user);
        User createdUser = myUserService.saveUser(user);
        logger.info("User created successfully with ID {}", createdUser.getId());
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/userid/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User user = myUserService.getUserById(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/user/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = myUserService.getUserByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
