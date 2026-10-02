package com.muhammadakram.campus_placement_portal.repository;

import com.muhammadakram.campus_placement_portal.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
}