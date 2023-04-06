package com.authentication.demo.savedjob;

import com.authentication.demo.savedjob.dto.SavedJobPaginationDto;
import com.authentication.demo.savedjob.request.CreateSavedJobRequest;
import com.authentication.demo.savedjob.response.CreateSavedJobResponse;
import com.authentication.demo.savedjob.response.GetSavedJobsResponse;
import com.authentication.demo.savedjob.response.SyncSavedJobResponse;
import com.authentication.demo.savedjob.response.DeleteSavedJobResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/saved-jobs")

public class SavedJobController {
    @Autowired
    private final SavedJobService savedJobService;

    public SavedJobController(SavedJobService savedJobService) {
        this.savedJobService = savedJobService;
    }

    @GetMapping("/sync/")
    public ResponseEntity<SyncSavedJobResponse> syncSavedJob(@RequestParam("jobId") Long jobId) {
        SavedJob savedJob = this.savedJobService.syncSavedJob(jobId);
        Boolean savedJobExists = savedJob == null ? false : true;
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SyncSavedJobResponse(savedJobExists, savedJob != null ? savedJob.getId() : null));
    }

    @PostMapping("/")
    public ResponseEntity<CreateSavedJobResponse> createSavedJob(@RequestBody CreateSavedJobRequest request) {
        this.savedJobService.createSavedJob(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreateSavedJobResponse("Success"));
    }

    @GetMapping("/")
    public ResponseEntity<GetSavedJobsResponse> getEmployerJobs(@RequestParam("userId") String userId,
            @RequestParam("page") String page, @RequestParam("size") String size,
            @RequestParam("direction") String direction) {

        SavedJobPaginationDto jobPagination = this.savedJobService
                .getSavedJobs(Long.parseLong(userId), Integer.valueOf(page),
                        Integer.valueOf(size), direction);

        return ResponseEntity.status(HttpStatus.OK).body(
                new GetSavedJobsResponse(
                        jobPagination.getPage(),
                        Integer.valueOf(size),
                        jobPagination.getJobDto(),
                        jobPagination.getTotalPages()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteSavedJobResponse> deleteSavedJob(@PathVariable("id") Long id) {

        this.savedJobService.deleteSavedJob(id);
        return ResponseEntity.status(HttpStatus.OK).body(new DeleteSavedJobResponse("Success"));
    }

}
