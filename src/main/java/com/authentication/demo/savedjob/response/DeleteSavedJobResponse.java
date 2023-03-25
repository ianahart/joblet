package com.authentication.demo.savedjob.response;

public class DeleteSavedJobResponse {
    private String message;

    public DeleteSavedJobResponse() {
    }

    public DeleteSavedJobResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
