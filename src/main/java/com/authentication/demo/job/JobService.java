package com.authentication.demo.job;

import com.authentication.demo.employer.Employer;
import com.authentication.demo.employer.EmployerRepository;
import com.authentication.demo.job.dto.JobDto;
import com.authentication.demo.job.dto.JobPaginationDto;
import com.authentication.demo.job.dto.SyncJobDto;
import com.authentication.demo.job.dto.ViewJobDto;
import com.authentication.demo.job.request.CreateJobRequest;
import com.authentication.demo.job.request.UpdateJobRequest;
import com.authentication.demo.job.response.DeleteJobResponse;
import com.authentication.demo.search.SearchService;
import com.authentication.demo.user.User;
import com.authentication.demo.user.UserRepository;
import com.authentication.demo.util.MyUtils;
import com.authentication.demo.advice.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

import com.authentication.demo.advice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class JobService {

    @Autowired
    private final SearchService searchService;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JobRepository jobRepository;

    @Autowired
    final EmployerRepository employerRepository;

    public JobService(JobRepository jobRepository, EmployerRepository employerRepository,
            UserRepository userRepository, SearchService searchService) {
        this.jobRepository = jobRepository;
        this.employerRepository = employerRepository;
        this.userRepository = userRepository;
        this.searchService = searchService;

    }

    public SyncJobDto syncJob(Long id) {
        return this.jobRepository.syncJob(id);
    }

    public DeleteJobResponse deleteEmployerJob(Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found."));

        Job job = this.jobRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Job not found."));

        if (job.getEmployer().getId() != user.getEmployer().getId()) {
            throw new ForbiddenException("Cannot delete a job that is not yours.");
        }

        this.jobRepository.deleteById(id);
        return new DeleteJobResponse("Success");
    }

    public ViewJobDto getEmployerJob(Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found."));
        ViewJobDto employerJob = this.jobRepository.findJobById(id);

        employerJob.setReadableDate(MyUtils.makeReadableDate(employerJob.getCreatedAt()));
        if (user.getEmployer().getId() != employerJob.getEmployerId()) {
            throw new ForbiddenException("Cannot view another employer's job listing.");
        }
        return employerJob;
    }

    public JobPaginationDto getJobsSearch(Integer page, Integer size, String direction, String q,
            HttpServletRequest request) {

        String userName = "";
        Object auth = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (auth instanceof UserDetails) {
            userName = ((UserDetails) auth).getUsername();
        }

        User user = this.userRepository
                .findByEmail(userName)
                .orElseThrow(() -> new NotFoundException("User not found."));
        this.searchService.createSearch(user, q);

        Integer currentPage = MyUtils.paginate(page, direction);

        Pageable paging = PageRequest.of(currentPage, size, Sort.by("id"));
        Page<JobDto> pagedResult = this.jobRepository.findJobsSearch(q.toLowerCase(), paging);

        return new JobPaginationDto(pagedResult.getContent(), pagedResult.getTotalPages(), currentPage);

    }

    public JobPaginationDto getJobs(Integer page, Integer size, String direction) {
        Integer currentPage = MyUtils.paginate(page, direction);

        Pageable paging = PageRequest.of(currentPage, size, Sort.by("id"));
        Page<JobDto> pagedResult = this.jobRepository.findJobs(paging);

        return new JobPaginationDto(pagedResult.getContent(), pagedResult.getTotalPages(), currentPage);

    }

    public JobPaginationDto getEmployerJobs(Long employerId, Integer page, Integer size, String direction) {

        Integer currentPage = MyUtils.paginate(page, direction);

        Pageable paging = PageRequest.of(currentPage, size, Sort.by("id"));
        Page<JobDto> pagedResult = this.jobRepository.findJobsByEmployerId(employerId, paging);

        return new JobPaginationDto(pagedResult.getContent(), pagedResult.getTotalPages(), currentPage);

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

        return this.jobRepository.save(currentJob);

    }

    public ViewJobDto getJob(Long jobId) {
        ViewJobDto job = this.jobRepository.findJobById(jobId);
        job.setReadableDate(MyUtils.makeReadableDate(job.getCreatedAt()));
        return job;

    }
}
