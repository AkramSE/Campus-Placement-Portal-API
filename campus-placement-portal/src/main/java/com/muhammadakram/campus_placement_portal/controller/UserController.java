package com.muhammadakram.campus_placement_portal.controller;

import com.muhammadakram.campus_placement_portal.entity.User;
import com.muhammadakram.campus_placement_portal.repository.UserRepository;
import com.muhammadakram.campus_placement_portal.security.JwtUtil;
import com.muhammadakram.campus_placement_portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    // Database se direct user check karne ke liye
    @Autowired
    private UserRepository userRepository;

    // Password ko Encrypt karne wala tool (Jo humne SecurityConfig mein banaya tha)
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Token Generate karne wala tool
    @Autowired
    private JwtUtil jwtUtil;

    // ==========================================
    // 1. REGISTRATION API (With Password Encryption)
    // ==========================================
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // Pehle check karein ke email pehle se majood toh nahi
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        // User ke password ko Encrypt (Hash) karna
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword); // Hashed password set kar diya

        // Database mein save karna
        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    // ==========================================
    // 2. LOGIN API (With JWT Token Generation)
    // ==========================================
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginData) {
        // Step 1: Database se user email ke zariye nikalna
        User existingUser = userRepository.findByEmail(loginData.getEmail());

        if (existingUser == null) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }

        // Step 2: Password Match karna (User ka dala hua vs Database ka Hashed Password)
        boolean isPasswordMatch = passwordEncoder.matches(loginData.getPassword(), existingUser.getPassword());

        if (!isPasswordMatch) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }

        // Step 3: Agar password theek hai toh JWT Token generate karna
        String token = jwtUtil.generateToken(existingUser.getEmail());

        // Step 4: Token aur User Data React (Frontend) ko wapas bhejna
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", existingUser);

        return ResponseEntity.ok(response);
    }
}