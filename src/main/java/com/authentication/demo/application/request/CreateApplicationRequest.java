package com.authentication.demo.application.request;

public class CreateApplicationRequest {

    private Long jobId;
    private Long employerId;
    private Long userId;
    private Long profileId;
    private String jobCompany;
    private String jobPosition;

    public CreateApplicationRequest(Long profileId, Long userId, Long jobId, Long employerId, String jobCompany,
            String jobPosition) {
        this.profileId = profileId;
        this.userId = userId;
        this.jobId = jobId;
        this.employerId = employerId;
        this.jobCompany = jobCompany;
        this.jobPosition = jobPosition;
    }

    public Long getJobId() {
        return jobId;
    }

    public String getJobCompany() {
        return jobCompany;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getProfileId() {
        return profileId;
    }

    public Long getEmployerId() {
        return employerId;
    }

    public void setJob(Long jobId) {
        this.jobId = jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public void setJobCompany(String jobCompany) {
        this.jobCompany = jobCompany;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
    }
}
