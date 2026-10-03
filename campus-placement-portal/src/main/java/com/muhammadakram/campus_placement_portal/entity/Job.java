package com.muhammadakram.campus_placement_portal.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // React se aane wale data ko map karne ke liye
    @JsonProperty("company_name")
    private String companyName;

    private String description;

    private String status;

    private String jobType;

    private String deadline;
}