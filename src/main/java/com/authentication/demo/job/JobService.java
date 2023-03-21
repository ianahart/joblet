package com.authentication.demo.job;

import com.authentication.demo.employer.Employer;
import com.authentication.demo.employer.EmployerRepository;
import com.authentication.demo.job.dto.JobDto;
import com.authentication.demo.job.dto.JobPaginationDto;
import com.authentication.demo.job.dto.ViewJobDto;
import com.authentication.demo.job.request.CreateJobRequest;
import com.authentication.demo.job.request.UpdateJobRequest;
import com.authentication.demo.user.User;
import com.authentication.demo.user.UserRepository;
import com.authentication.demo.util.MyUtils;
import com.authentication.demo.advice.NotFoundException;

import com.authentication.demo.advice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JobRepository jobRepository;

    @Autowired
    final EmployerRepository employerRepository;

    public JobService(JobRepository jobRepository, EmployerRepository employerRepository,
            UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.employerRepository = employerRepository;
        this.userRepository = userRepository;

    }

    public ViewJobDto getEmployerJob(Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found."));
        ViewJobDto employerJob = this.jobRepository.findJobByEmployerId(id);
        if (user.getEmployer().getId() != employerJob.getEmployerId()) {
            throw new ForbiddenException("Cannot view another employer's job listing.");
        }
        return employerJob;
    }

    public JobPaginationDto getEmployerJobs(Long employerId, Integer page, Integer size, String direction) {

        Integer currentPage = page;

        if (direction.equals("prev") && currentPage > 0) {
            currentPage = currentPage - 1;
        }
        if (direction.equals("next")) {
            currentPage = currentPage + 1;
        }

        Pageable paging = PageRequest.of(currentPage, size, Sort.by("id"));
        Page<JobDto> pagedResult = this.jobRepository.findJobsByEmployerId(employerId, paging);

        return new JobPaginationDto(pagedResult.getContent(), pagedResult.getTotalPages(), currentPage);

    }

    public void createJob(CreateJobRequest request) {
        Employer employer = this.employerRepository.findById(request.getEmployerId())
                .orElseThrow(() -> new NotFoundException("Employer not found."));

        System.out.println(employer.getId());
        System.out.println(request);
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
