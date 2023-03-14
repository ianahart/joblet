package com.authentication.demo.profile.request;

public class DeleteResumeRequest {
    private String resume;
    private String fileName;

    public DeleteResumeRequest() {

    }

    public DeleteResumeRequest(String resume, String fileName) {
        this.resume = resume;
        this.fileName = fileName;
    }

    public String getResume() {
        return resume;
    }

    public String getFileName() {
        return fileName;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
