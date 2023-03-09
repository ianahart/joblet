package com.authentication.demo.profile.request;

import org.springframework.web.multipart.MultipartFile;

public class UploadPDFRequest {

    private MultipartFile file;

    public UploadPDFRequest() {

    }

    public UploadPDFRequest(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
