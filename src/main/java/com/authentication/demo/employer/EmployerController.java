package com.authentication.demo.employer;

import com.authentication.demo.employer.request.CreateEmployerRequest;
import com.authentication.demo.employer.request.UpdateEmployerRequest;
import com.authentication.demo.employer.response.CreateEmployerResponse;
import com.authentication.demo.employer.response.UpdateEmployerResponse;

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
@RequestMapping(path = "/api/v1/employers")
public class EmployerController {
    @Autowired
    private final EmployerService employerService;

    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @PostMapping(path = "/")
    public ResponseEntity<CreateEmployerResponse> createEmployer(@Valid @RequestBody CreateEmployerRequest request) {
        Employer employer = this.employerService.createEmployer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new CreateEmployerResponse(
                        employer.getId(),
                        employer.getCompanyName(),
                        employer.getNumOfEmployees(),
                        employer.getFirstName(),
                        employer.getLastName(),
                        employer.getEmail(),
                        employer.getCreatedAt(),
                        employer.getLocation()));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<UpdateEmployerResponse> updateEmployer(@Valid @RequestBody UpdateEmployerRequest request,
            @PathVariable("id") Long id) {
        Employer employer = this.employerService.updateEmployer(request, id);

        return ResponseEntity.status(HttpStatus.OK).body(new UpdateEmployerResponse(
                employer.getId(),
                employer.getCompanyName(),
                employer.getNumOfEmployees(),
                employer.getFirstName(),
                employer.getLastName(),
                employer.getEmail(),
                employer.getCreatedAt(),
                employer.getLocation()));

    }
}
