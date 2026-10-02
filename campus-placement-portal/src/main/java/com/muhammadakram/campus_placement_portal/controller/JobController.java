package com.muhammadakram.campus_placement_portal.controller;

import com.muhammadakram.campus_placement_portal.entity.Job;
import com.muhammadakram.campus_placement_portal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    // API: Nayi job lagane ke liye
    @PostMapping("/post")
    public Job postJob(@RequestBody Job job) {
        return jobService.postJob(job);
    }

    // API: Sari jobs ki list dekhne ke liye
    @GetMapping("/all")
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }
}