package com.authentication.demo.job;

import com.authentication.demo.job.request.CreateJobRequest;
import com.authentication.demo.job.request.UpdateJobRequest;
import com.authentication.demo.job.response.UpdateJobResponse;
import com.authentication.demo.job.response.CreateJobResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
