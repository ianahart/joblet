package com.authentication.demo.search.response;

public class DeleteSearchResponse {
    private String message;

    public DeleteSearchResponse() {

    }

    public DeleteSearchResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
