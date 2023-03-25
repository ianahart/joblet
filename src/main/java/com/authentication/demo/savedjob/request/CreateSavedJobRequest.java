package com.authentication.demo.savedjob.request;

public class CreateSavedJobRequest {
    private Long jobId;
    private Long userId;


    public CreateSavedJobRequest(){}

    public CreateSavedJobRequest(Long jobId, Long userId) {
       this.jobId = jobId;
        this.userId = userId;
    }


    public Long getJobId() {
        return jobId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
