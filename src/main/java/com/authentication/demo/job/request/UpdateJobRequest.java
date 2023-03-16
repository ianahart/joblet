package com.authentication.demo.job.request;

import com.authentication.demo.employer.Employer;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

public class UpdateJobRequest {
    @Size(max = 175, message = "Please keep position under 175 characters")
    private String position;
    @Max(value = 1000000, message = "Please keep number of employees under 1 million.")
    private Integer perHour;
    private Long employerId;
    @Size(max = 175, message = "Please keep position under 175 characters")
    private String availability;
    private Boolean urgentlyHiring;
    private Boolean multipleCandidates;
    @Size(max = 4000, message = "Please keep position under 4000 characters")
    private String body;

    public UpdateJobRequest() {
    }

    public UpdateJobRequest(
            String position,
            Integer perHour,
            Long employerId,
            String availability,
            Boolean urgentlyHiring,
            Boolean multipleCandidates,
            String body) {
        this.position = position;
        this.perHour = perHour;
        this.employerId = employerId;
        this.availability = availability;
        this.urgentlyHiring = urgentlyHiring;
        this.multipleCandidates = multipleCandidates;
        this.body = body;

    }

    public String getBody() {
        return body;
    }

    public Integer getPerHour() {
        return perHour;
    }

    public String getPosition() {
        return position;
    }

    public Boolean getMultipleCandidates() {
        return multipleCandidates;
    }

    public Boolean getUrgentlyHiring() {
        return urgentlyHiring;
    }

    public String getAvailability() {
        return availability;
    }

    public Long getEmployerId() {
        return employerId;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setPerHour(Integer perHour) {
        this.perHour = perHour;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public void setUrgentlyHiring(Boolean urgentlyHiring) {
        this.urgentlyHiring = urgentlyHiring;
    }

    public void setMultipleCandidates(Boolean multipleCandidates) {
        this.multipleCandidates = multipleCandidates;
    }

}
