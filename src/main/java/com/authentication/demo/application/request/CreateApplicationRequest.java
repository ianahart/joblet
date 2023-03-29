package com.authentication.demo.application.request;

import com.authentication.demo.employer.Employer;
import com.authentication.demo.job.Job;
import com.authentication.demo.profile.Profile;
import com.authentication.demo.user.User;

public class CreateApplicationRequest {

    private Long jobId;
    private Long employerId;
    private Long userId;
    private Long profileId;

    public CreateApplicationRequest(Long profileId, Long userId, Long jobId, Long employerId) {
        this.profileId = profileId;
        this.userId = userId;
        this.jobId = jobId;
        this.employerId = employerId;
    }

    public Long getJobId() {
        return jobId;
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
