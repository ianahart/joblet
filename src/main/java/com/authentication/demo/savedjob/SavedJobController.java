package com.authentication.demo.savedjob;

import com.authentication.demo.savedjob.request.CreateSavedJobRequest;
import com.authentication.demo.savedjob.response.CreateSavedJobResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/saved-jobs")

public class SavedJobController {
    @Autowired
    private final SavedJobService savedJobService;

    public SavedJobController(SavedJobService savedJobService) {
        this.savedJobService = savedJobService;
    }

    @PostMapping("/")
    public ResponseEntity<CreateSavedJobResponse> createSavedJob(@RequestBody CreateSavedJobRequest request) {
        this.savedJobService.createSavedJob(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreateSavedJobResponse("Success"));
    }
}
