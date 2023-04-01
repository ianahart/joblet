package com.authentication.demo.application;

import com.authentication.demo.application.dto.PagedApplicationDto;
import com.authentication.demo.application.request.CreateApplicationRequest;
import com.authentication.demo.application.response.DeleteApplicationResponse;
import com.authentication.demo.application.response.GetApplicationResponse;

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
@RequestMapping(path = "/api/v1/applications")

public class ApplicationController {
    @Autowired
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetApplicationResponse> getApplication(@PathVariable("id") Long id,
            @RequestParam("jobId") Long jobId, @RequestParam("profileId") Long profileId,
            @RequestParam("userId") Long userId) {

        return ResponseEntity.status(HttpStatus.OK).body(
                new GetApplicationResponse(applicationService.getApplication(id, jobId, profileId, userId)));

    }

    @PostMapping("/")
    public void createApplication(@RequestBody CreateApplicationRequest request) {
        Application application = this.applicationService.createApplication(request);
    }

    @GetMapping("/")
    public ResponseEntity<PagedApplicationDto> getEmployerJobs(@RequestParam("employerId") String employerId,
            @RequestParam("page") String page, @RequestParam("size") String size,
            @RequestParam("direction") String direction) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(this.applicationService.getApplications(Long.valueOf(employerId), Integer.valueOf(page),
                        direction, Integer.valueOf(size)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteApplicationResponse> deleteApplication(@PathVariable("id") Long id) {
        this.applicationService.deleteApplication(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new DeleteApplicationResponse("Success"));
    }

}
