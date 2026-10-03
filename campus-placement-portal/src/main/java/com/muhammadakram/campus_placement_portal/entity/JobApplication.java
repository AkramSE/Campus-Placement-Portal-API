package com.muhammadakram.campus_placement_portal.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "job_applications")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId; // Kis student ne apply kiya
    private Long jobId; // Kis job par apply kiya

    private String jobTitle; // Job ka title (Frontend par dikhane ke liye)

    @JsonProperty("company_name")
    private String companyName; // Company ka naam

    private String studentName; // Apply karne wale student ka naam
    private String studentEmail; // Apply karne wale student ka email

    private String resumeLink; // Student ki CV (Resume/LinkedIn) ka link

    private String status = "PENDING"; // Default status hamesha PENDING rahega (PENDING, ACCEPTED, REJECTED)

    private LocalDateTime appliedAt = LocalDateTime.now(); // Kis waqt apply kiya, automatically save hoga
}