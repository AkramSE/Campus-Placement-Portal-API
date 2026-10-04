package com.muhammadakram.campus_placement_portal.controller;

import com.muhammadakram.campus_placement_portal.entity.User;
import com.muhammadakram.campus_placement_portal.repository.UserRepository;
import com.muhammadakram.campus_placement_portal.security.JwtUtil;
import com.muhammadakram.campus_placement_portal.service.EmailService;
import com.muhammadakram.campus_placement_portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EmailService emailService;

    // ==========================================
    // 1. REGISTRATION API
    // ==========================================
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    // ==========================================
    // 2. LOGIN API
    // ==========================================
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginData) {
        User existingUser = userRepository.findByEmail(loginData.getEmail());

        if (existingUser == null) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }

        boolean isPasswordMatch = passwordEncoder.matches(loginData.getPassword(), existingUser.getPassword());

        if (!isPasswordMatch) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }

        String token = jwtUtil.generateToken(existingUser.getEmail());

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", existingUser);

        return ResponseEntity.ok(response);
    }

    // ==========================================
    // 3. FORGOT PASSWORD API (Generate OTP)
    // ==========================================
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        User user = userRepository.findByEmail(email);

        if (user == null) {
            return ResponseEntity.badRequest().body("Error: No account found with this email!");
        }

        String otp = String.format("%06d", new Random().nextInt(999999));

        user.setOtp(otp);
        userRepository.save(user);

        emailService.sendOtpEmail(email, otp);

        return ResponseEntity.ok("OTP has been sent to your email.");
    }

    // ==========================================
    // 4. RESET PASSWORD API (Verify OTP & Update Password)
    // ==========================================
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");
        String newPassword = request.get("newPassword");

        User user = userRepository.findByEmail(email);

        if (user == null) {
            return ResponseEntity.badRequest().body("Error: User not found!");
        }

        if (user.getOtp() == null || !user.getOtp().equals(otp)) {
            return ResponseEntity.badRequest().body("Error: Invalid or expired OTP!");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setOtp(null);
        userRepository.save(user);

        return ResponseEntity.ok("Password has been reset successfully. You can now login.");
    }

    // ==========================================
    // 5. UPDATE PROFILE API (Name, Phone, Profile Image)
    // ==========================================
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUserProfile(@PathVariable Long id, @RequestBody User updatedData) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Error: User not found!");
        }

        User existingUser = userOptional.get();

        // Agar naya data aaya hai toh usay update kar do
        if (updatedData.getName() != null) {
            existingUser.setName(updatedData.getName());
        }
        if (updatedData.getPhone() != null) {
            existingUser.setPhone(updatedData.getPhone());
        }
        if (updatedData.getProfileImage() != null) {
            existingUser.setProfileImage(updatedData.getProfileImage());
        }

        // Database mein update save karna
        User savedUser = userRepository.save(existingUser);

        return ResponseEntity.ok(savedUser);
    }
}