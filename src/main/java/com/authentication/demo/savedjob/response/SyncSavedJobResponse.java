package com.authentication.demo.savedjob.response;

public class SyncSavedJobResponse {
    private Boolean isSaved;
    private Long savedJobId;

    public SyncSavedJobResponse() {
    }

    public SyncSavedJobResponse(Boolean isSaved, Long savedJobId) {
        this.isSaved = isSaved;
        this.savedJobId = savedJobId;
    }

    public Boolean getIsSaved() {
        return isSaved;
    }

    public Long getSavedJobId() {
        return savedJobId;
    }

    public void setIsSaved(Boolean isSaved) {
        this.isSaved = isSaved;
    }

    public void setSavedJobId(Long savedJobId) {
        this.savedJobId = savedJobId;
    }
}
