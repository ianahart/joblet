package com.authentication.demo.savedjob.request;

public class CreateSavedJobRequest {
    private Long jobId;
    private Long userId;
    private Long employerId;

    public CreateSavedJobRequest() {
    }

    public CreateSavedJobRequest(Long jobId, Long userId, Long employerId) {
        this.jobId = jobId;
        this.userId = userId;
        this.employerId = employerId;
    }

    public Long getJobId() {
        return jobId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getEmployerId() {
        return employerId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
    }
}
