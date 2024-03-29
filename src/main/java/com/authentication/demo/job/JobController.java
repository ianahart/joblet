package com.authentication.demo.job;


import com.authentication.demo.job.dto.JobPaginationDto;
import com.authentication.demo.job.dto.SyncJobDto;
import com.authentication.demo.job.dto.ViewJobDto;
import com.authentication.demo.job.request.CreateJobRequest;
import com.authentication.demo.job.request.UpdateJobRequest;
import com.authentication.demo.job.response.UpdateJobResponse;
import com.authentication.demo.job.response.CreateJobResponse;
import com.authentication.demo.job.response.DeleteJobResponse;
import com.authentication.demo.job.response.GetJobsResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping(path = "/api/v1/jobs")
public class JobController {

    @Autowired
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/search")
    public ResponseEntity<GetJobsResponse> getJobsSearch(
            @RequestParam("page") String page, @RequestParam("size") String size,
            @RequestParam("direction") String direction, @RequestParam("q") String q, HttpServletRequest request) {

        JobPaginationDto jobPagination = this.jobService
                .getJobsSearch(Integer.valueOf(page),
                        Integer.valueOf(size), direction, q, request);

        return ResponseEntity.status(HttpStatus.OK).body(
                new GetJobsResponse(
                        jobPagination.getPage(),
                        Integer.valueOf(size),
                        jobPagination.getJobDto(),
                        jobPagination.getTotalPages()));
    }

    @GetMapping("/")
    public ResponseEntity<GetJobsResponse> getJobs(
            @RequestParam("page") String page, @RequestParam("size") String size,
            @RequestParam("direction") String direction) {

        JobPaginationDto jobPagination = this.jobService
                .getJobs(Integer.valueOf(page),
                        Integer.valueOf(size), direction);

        return ResponseEntity.status(HttpStatus.OK).body(
                new GetJobsResponse(
                        jobPagination.getPage(),
                        Integer.valueOf(size),
                        jobPagination.getJobDto(),
                        jobPagination.getTotalPages()));
    }

    @DeleteMapping("owner/{id}")
    public ResponseEntity<DeleteJobResponse> deleteJob(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.jobService.deleteEmployerJob(id));
    }

    @GetMapping("owner/{id}/sync")
    public ResponseEntity<SyncJobDto> getSyncJob(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.jobService.syncJob(id));
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<ViewJobDto> getEmployerJob(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.jobService.getEmployerJob(id));
    }

    @GetMapping("/owner")
    public ResponseEntity<GetJobsResponse> getEmployerJobs(@RequestParam("employerId") String employerId,
            @RequestParam("page") String page, @RequestParam("size") String size,
            @RequestParam("direction") String direction) {

        JobPaginationDto jobPagination = this.jobService
                .getEmployerJobs(Long.parseLong(employerId), Integer.valueOf(page),
                        Integer.valueOf(size), direction);

        return ResponseEntity.status(HttpStatus.OK).body(
                new GetJobsResponse(
                        jobPagination.getPage(),
                        Integer.valueOf(size),
                        jobPagination.getJobDto(),
                        jobPagination.getTotalPages()));
    }

    @PostMapping("/")
    public ResponseEntity<CreateJobResponse> createJob(@Valid @RequestBody CreateJobRequest request) {
        this.jobService.createJob(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreateJobResponse("Success"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateJobResponse> updateJob(@Valid @RequestBody UpdateJobRequest request,
            @PathVariable("id") Long id) {
        Job job = this.jobService.updateJob(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new UpdateJobResponse(
                        job.getId(),
                        job.getPosition(),
                        job.getPerHour(),
                        job.getAvailability(),
                        job.getUrgentlyHiring(),
                        job.getMultipleCandidates(),
                        job.getBody()));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ViewJobDto> getJob(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.jobService.getJob(id));
    }
}
