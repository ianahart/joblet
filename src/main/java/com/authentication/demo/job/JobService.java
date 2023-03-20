package com.authentication.demo.job;

import com.authentication.demo.employer.Employer;
import com.authentication.demo.employer.EmployerRepository;
import com.authentication.demo.job.dto.JobDto;
import com.authentication.demo.job.dto.JobPaginationDto;
import com.authentication.demo.job.request.CreateJobRequest;
import com.authentication.demo.job.request.UpdateJobRequest;
import com.authentication.demo.util.MyUtils;
import com.authentication.demo.advice.NotFoundException;

import com.authentication.demo.advice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

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

    public JobPaginationDto getJobs(Long employerId, Integer page, Integer size, String direction) {

        Pageable paging = PageRequest.of(page, size, Sort.by("id"));
        Page<JobDto> pagedResult = this.jobRepository.findJobsByEmployerId(employerId, paging);
        if (pagedResult.getContent().size() > 0) {
            if (direction.equals("next") && page < pagedResult.getTotalPages()) {
                page = page + 1;
            } else if (direction.equals("prev") && page > 1) {
                page = page - 1;
            }
        }
        if (pagedResult.hasContent()) {

            System.out.println(pagedResult.getNumber());
            return new JobPaginationDto(pagedResult.getContent(), pagedResult.getTotalPages(), page);

        } else {
            return new JobPaginationDto(pagedResult.getContent(), 0, page);
        }
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
