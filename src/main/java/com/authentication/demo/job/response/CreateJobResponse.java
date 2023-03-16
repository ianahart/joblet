package com.authentication.demo.job.response;

public class CreateJobResponse {
    private String message;

    public CreateJobResponse() {
    }

    public CreateJobResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
