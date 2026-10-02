package com.muhammadakram.campus_placement_portal.controller;

import com.muhammadakram.campus_placement_portal.entity.User;
import com.muhammadakram.campus_placement_portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Registration API
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // Login API
    @PostMapping("/login")
    public User loginUser(@RequestBody User loginData) {
        User user = userService.loginUser(loginData.getEmail(), loginData.getPassword());
        if (user == null) {
            throw new RuntimeException("Invalid email or password");
        }
        return user;
    }
}