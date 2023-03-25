package com.authentication.demo.savedjob.response;

public class CreateSavedJobResponse {
    private String message;


    public CreateSavedJobResponse() {}

    public CreateSavedJobResponse(String message) {
       this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
