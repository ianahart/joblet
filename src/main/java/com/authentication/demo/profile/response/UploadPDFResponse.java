package com.authentication.demo.profile.response;

public class UploadPDFResponse {
    private String resume;

    public UploadPDFResponse() {

    }

    public UploadPDFResponse(String resume) {
        this.resume = resume;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
}
