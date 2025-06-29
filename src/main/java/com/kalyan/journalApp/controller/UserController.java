package com.kalyan.journalApp.controller;

import com.kalyan.journalApp.api.response.WhetherResponse;
import com.kalyan.journalApp.entity.User;
import com.kalyan.journalApp.repository.UserRepository;
import com.kalyan.journalApp.service.UserService;
import com.kalyan.journalApp.service.WhetherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "User APIs", description = "Read, Update & Delete User")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WhetherService whetherService;


    // Get all users
    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    // Update user details
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userService.findByUsername(username);

        // Ensure the user exists
        if (userInDb == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        userInDb.setUsername(user.getUsername());

        // Encode the password if it's being updated
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            userInDb.setPassword(passwordEncoder.encode(user.getPassword())); // Encode the password
        }

        userService.saveNewUser(userInDb); // Save updated user
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Delete the current authenticated user
    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userService.findByUsername(username);

        // Ensure the user exists before attempting deletion
        if (userInDb == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        userRepository.delete(userInDb); // Delete by user object
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/greeting")
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String greeting = "Hi " + authentication.getName();

        WhetherResponse whetherResponse = whetherService.getWhether("Hyderabad"); // or city from DB
        if (whetherResponse != null && whetherResponse.getCurrent() != null) {
            greeting += ", weather feels like " + whetherResponse.getCurrent().getFeelslike() + "°C";
        }

        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }

}
