package com.muhammadakram.campus_placement_portal.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String role; // STUDENT, COMPANY, ya ADMIN

    // ==========================================
    // Naya column OTP save karne ke liye
    // ==========================================
    private String otp;

    // ==========================================
    // Profile Update ke naye columns
    // ==========================================
    private String phone;

    @Lob // @Lob ka matlab hai ke isme bari file (tasweer) save hogi
    @Column(columnDefinition = "LONGTEXT")
    private String profileImage;
}