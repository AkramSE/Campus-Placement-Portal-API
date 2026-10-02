package com.muhammadakram.campus_placement_portal.controller;

import com.muhammadakram.campus_placement_portal.entity.JobApplication;
import com.muhammadakram.campus_placement_portal.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    @PostMapping("/apply")
    public JobApplication applyForJob(@RequestBody JobApplication application) {
        return jobApplicationService.applyForJob(application);
    }

    @GetMapping("/all")
    public List<JobApplication> getAllApplications() {
        return jobApplicationService.getAllApplications();
    }

    // API: HR Application Accept/Reject karega (Nayi API)
    @PutMapping("/{id}/status")
    public JobApplication updateStatus(@PathVariable Long id, @RequestParam String status) {
        return jobApplicationService.updateStatus(id, status);
    }
}