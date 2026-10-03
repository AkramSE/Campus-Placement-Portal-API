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

    public JobApplication saveApplication(JobApplication application) {
        return jobApplicationRepository.save(application);
    }

    public List<JobApplication> getApplicationsForCompany(String companyName) {
        return jobApplicationRepository.findByCompanyName(companyName);
    }

    // NAYA METHOD: Status Update Karne Ke Liye
    public JobApplication updateApplicationStatus(Long id, String status) {
        JobApplication application = jobApplicationRepository.findById(id).orElse(null);
        if (application != null) {
            application.setStatus(status); // PENDING se ACCEPTED ya REJECTED ho jayega
            return jobApplicationRepository.save(application);
        }
        return null;
    }
}