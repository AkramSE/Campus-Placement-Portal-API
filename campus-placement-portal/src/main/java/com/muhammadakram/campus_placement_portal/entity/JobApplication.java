package com.muhammadakram.campus_placement_portal.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "job_applications")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId; // Kis student ne apply kiya

    private Long jobId; // Kis job par apply kiya

    private String resumeLink; // Student ki CV (Resume) ka link

    private String status; // PENDING, ACCEPTED, ya REJECTED
}