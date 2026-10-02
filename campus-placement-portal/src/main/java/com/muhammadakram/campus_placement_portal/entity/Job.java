package com.muhammadakram.campus_placement_portal.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // e.g., "Software Engineering Intern"

    private String companyName; // e.g., "10Pearls"

    private String description; // Job ki details

    private String status; // OPEN ya CLOSED
}