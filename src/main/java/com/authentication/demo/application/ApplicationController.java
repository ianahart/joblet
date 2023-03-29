package com.authentication.demo.application;

import com.authentication.demo.application.request.CreateApplicationRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/applications")

public class ApplicationController {
    @Autowired
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/")
    public void createApplication(@RequestBody CreateApplicationRequest request) {
        Application application = this.applicationService.createApplication(request);
        String name = application.getEmployer().getCompanyName();
        System.out.println(name);
    }
}
