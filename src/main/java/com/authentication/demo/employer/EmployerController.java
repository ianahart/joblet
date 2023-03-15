package com.authentication.demo.employer;

import com.authentication.demo.employer.request.CreateEmployerRequest;
import com.authentication.demo.employer.response.CreateEmployerResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/employers")
public class EmployerController {
    @Autowired
    private final EmployerService employerService;

    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @PostMapping(path = "/")
    public ResponseEntity<CreateEmployerResponse> createEmployer(@RequestBody CreateEmployerRequest request) {
        Employer employer = this.employerService.createEmployer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new CreateEmployerResponse(
                        employer.getCompanyName(),
                        employer.getNumOfEmployees(),
                        employer.getFirstName(),
                        employer.getLastName(),
                        employer.getEmail(),
                        employer.getCreatedAt()));
    }
}
