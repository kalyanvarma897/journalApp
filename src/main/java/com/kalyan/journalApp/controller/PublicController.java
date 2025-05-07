package com.kalyan.journalApp.controller;

import com.kalyan.journalApp.entity.User;
import com.kalyan.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    // Health Check endpoint
    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }

    // Endpoint to create a user
    @PostMapping("/create-user")
    public void createUser(@RequestBody User user) {
        userService.saveNewUser(user);
    }
}
