package com.authentication.demo.application.response;

public class DeleteApplicationResponse {
    private String message;

    public DeleteApplicationResponse() {
    }

    public DeleteApplicationResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
