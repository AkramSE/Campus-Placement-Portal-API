package com.muhammadakram.campus_placement_portal.repository;

import com.muhammadakram.campus_placement_portal.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    // Company ke naam se applications nikalne ke liye (Purana wala)
    List<JobApplication> findByCompanyName(String companyName);

    // NAYA: Student ID se uski bheji hui sari applications nikalne ke liye
    List<JobApplication> findByStudentId(Long studentId);
}