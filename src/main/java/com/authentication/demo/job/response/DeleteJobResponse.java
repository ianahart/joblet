package com.authentication.demo.job.response;

public class DeleteJobResponse {
    private String message;

    public DeleteJobResponse() {
    }

    public DeleteJobResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
