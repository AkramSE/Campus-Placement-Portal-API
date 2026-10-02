package com.muhammadakram.campus_placement_portal.service;

import com.muhammadakram.campus_placement_portal.entity.User;
import com.muhammadakram.campus_placement_portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Naye user ko save karne ke liye
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // Login check karne ke liye
    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);

        // Agar email database mein hai aur password bhi match kar jaye
        if (user != null && user.getPassword().equals(password)) {
            return user; // Login successful
        }
        return null; // Login failed
    }
}