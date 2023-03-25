package com.authentication.demo.savedjob;

import com.authentication.demo.advice.*;
import com.authentication.demo.job.Job;
import com.authentication.demo.job.JobRepository;
import com.authentication.demo.savedjob.request.CreateSavedJobRequest;
import com.authentication.demo.user.User;
import com.authentication.demo.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavedJobService {

    @Autowired
    private final SavedJobRepository savedJobRepository;

    @Autowired
    private final JobRepository jobRepository;

    @Autowired
    private final UserRepository userRepository;

    public SavedJobService(SavedJobRepository savedJobRepository,
            JobRepository jobRepository,
            UserRepository userRepository) {
        this.savedJobRepository = savedJobRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    public void createSavedJob(CreateSavedJobRequest request) {

        SavedJob exists = this.savedJobRepository
                .checkIfSavedJobExists(request.getJobId(), request.getUserId());
        if (exists != null) {
            throw new BadRequestException("User has already saved this job.");
        }

        Job job = this.jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new NotFoundException("Job not found."));
        User user = this.userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found."));
        SavedJob savedJob = new SavedJob(user, job);
        this.savedJobRepository.save(savedJob);
    }
}
