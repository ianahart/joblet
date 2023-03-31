package com.authentication.demo.application.dto;

import java.sql.Timestamp;

public interface ApplicationDto {
    Long getId();

    Timestamp getCreatedAt();

    String getJobCompany();

    String getJobPosition();

    Long getEmployerId();

    Long getProfileId();

    Long getUserId();

    Long getJobId();

}
