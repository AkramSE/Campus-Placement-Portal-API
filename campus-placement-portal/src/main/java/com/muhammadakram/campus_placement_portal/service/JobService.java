package com.muhammadakram.campus_placement_portal.service;

import com.muhammadakram.campus_placement_portal.entity.Job;
import com.muhammadakram.campus_placement_portal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    // HR ke liye: Nayi job post karne ka method
    public Job postJob(Job job) {
        job.setStatus("OPEN");
        return jobRepository.save(job);
    }

    // Student ke liye: Sari jobs dekhne ka method
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
}