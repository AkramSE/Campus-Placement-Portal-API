package com.muhammadakram.campus_placement_portal.service;

import com.muhammadakram.campus_placement_portal.entity.JobApplication;
import com.muhammadakram.campus_placement_portal.repository.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    // Student: Apply karega
    public JobApplication applyForJob(JobApplication application) {
        application.setStatus("PENDING");
        return jobApplicationRepository.save(application);
    }

    // HR: Sari applications dekhega
    public List<JobApplication> getAllApplications() {
        return jobApplicationRepository.findAll();
    }

    // HR: Application Accept ya Reject karega (Naya Method)
    public JobApplication updateStatus(Long id, String newStatus) {
        JobApplication app = jobApplicationRepository.findById(id).orElse(null);
        if (app != null) {
            app.setStatus(newStatus); // Status update ho raha hai
            return jobApplicationRepository.save(app);
        }
        throw new RuntimeException("Application nahi mili!");
    }
}