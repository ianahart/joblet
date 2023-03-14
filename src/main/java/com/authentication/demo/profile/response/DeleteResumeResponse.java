package com.authentication.demo.profile.response;

public class DeleteResumeResponse {
    private String message;

    public DeleteResumeResponse() {

    }

    public DeleteResumeResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
