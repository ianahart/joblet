package com.authentication.demo.application.response;

import com.authentication.demo.application.dto.ApplicationDetailsDto;

public class GetApplicationResponse {
    private ApplicationDetailsDto applicationDetailsDto;

    public GetApplicationResponse() {
    }

    public GetApplicationResponse(ApplicationDetailsDto applicationDetailsDto) {
        this.applicationDetailsDto = applicationDetailsDto;
    }

    public ApplicationDetailsDto getApplicationDetailsDto() {
        return applicationDetailsDto;
    }

    public void setApplicationDetailsDto(ApplicationDetailsDto applicationDetailsDto) {
        this.applicationDetailsDto = applicationDetailsDto;
    }
}
