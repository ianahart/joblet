package com.authentication.demo.savedjob.dto;

public interface SavedJobDto {

    Long getId();

    Long getUserId();

    Long getJobId();

    Boolean getUrgentlyHiring();

    Boolean getMultipleCandidates();

    String getAvailability();

    String getPosition();

    String getLocation();

    String getCompanyName();

}
