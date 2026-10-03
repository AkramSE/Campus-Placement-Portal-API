package com.muhammadakram.campus_placement_portal.controller;

import com.muhammadakram.campus_placement_portal.entity.JobApplication;
import com.muhammadakram.campus_placement_portal.repository.JobApplicationRepository;
import com.muhammadakram.campus_placement_portal.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class JobApplicationController {

    @Autowired
    private JobApplicationRepository applicationRepository;

    @Autowired
    private JobApplicationService jobApplicationService;

    @PostMapping("/apply")
    public JobApplication applyForJob(@RequestBody JobApplication application) {
        return applicationRepository.save(application);
    }

    @GetMapping("/all")
    public List<JobApplication> getAllApplications() {
        return applicationRepository.findAll();
    }

    @GetMapping("/company/{companyName}")
    public List<JobApplication> getCompanyApplications(@PathVariable String companyName) {
        return jobApplicationService.getApplicationsForCompany(companyName);
    }

    // HR Dashboard se Status Change karne ke liye
    @PutMapping("/{id}/status")
    public JobApplication updateStatus(@PathVariable Long id, @RequestParam String status) {
        return jobApplicationService.updateApplicationStatus(id, status);
    }

    // NAYI API: Student dashboard (My Applications) ke liye
    @GetMapping("/student/{studentId}")
    public List<JobApplication> getStudentApplications(@PathVariable Long studentId) {
        return applicationRepository.findByStudentId(studentId);
    }
}