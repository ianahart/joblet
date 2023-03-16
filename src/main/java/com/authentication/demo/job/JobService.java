package com.authentication.demo.job;

import com.authentication.demo.employer.Employer;
import com.authentication.demo.employer.EmployerRepository;
import com.authentication.demo.job.request.CreateJobRequest;
import com.authentication.demo.job.request.UpdateJobRequest;
import com.authentication.demo.util.MyUtils;
import com.authentication.demo.advice.NotFoundException;

import com.authentication.demo.advice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    private final JobRepository jobRepository;

    @Autowired
    final EmployerRepository employerRepository;

    public JobService(JobRepository jobRepository, EmployerRepository employerRepository) {
        this.jobRepository = jobRepository;
        this.employerRepository = employerRepository;

    }

    public void createJob(CreateJobRequest request) {
        Employer employer = this.employerRepository.findById(request.getEmployerId())
                .orElseThrow(() -> new NotFoundException("Employer not found."));

        Job job = new Job(MyUtils.titleCase(request.getPosition()), request.getPerHour(),
                MyUtils.titleCase(request.getAvailability()),
                request.getUrgentlyHiring(), request.getMultipleCandidates(), request.getBody(), employer);

        this.jobRepository.save(job);
    }

    public Job updateJob(UpdateJobRequest request, Long id) {
        Job currentJob = this.jobRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Job was not found."));

        currentJob.setPosition(MyUtils.titleCase(request.getPosition()));
        currentJob.setPerHour(request.getPerHour());
        currentJob.setAvailability(MyUtils.titleCase(request.getAvailability()));
        currentJob.setUrgentlyHiring(request.getUrgentlyHiring());
        currentJob.setMultipleCandidates(request.getMultipleCandidates());
        currentJob.setBody(request.getBody());

        Job updatedJob = this.jobRepository.save(currentJob);
        return updatedJob;
    }
}
