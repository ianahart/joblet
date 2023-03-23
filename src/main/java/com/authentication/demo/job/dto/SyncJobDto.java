package com.authentication.demo.job.dto;

public class SyncJobDto {
    private String position;
    private Integer perHour;
    private String availability;
    private Boolean urgentlyHiring;
    private Boolean multipleCandidates;
    private String body;
    private Long employerId;

    public SyncJobDto() {
    }

    public SyncJobDto(String position, Integer perHour, String availability,
            Boolean urgentlyHiring, Boolean multipleCandidates, String body, Long employerId) {

        this.position = position;
        this.perHour = perHour;
        this.availability = availability;
        this.urgentlyHiring = urgentlyHiring;
        this.multipleCandidates = multipleCandidates;
        this.body = body;
        this.employerId = employerId;
    }

    public Long getEmployerId() {
        return employerId;
    }

    public String getBody() {
        return body;
    }

    public Integer getPerHour() {
        return perHour;
    }

    public String getPosition() {
        return position;
    }

    public String getAvailability() {
        return availability;
    }

    public Boolean getUrgentlyHiring() {
        return urgentlyHiring;
    }

    public Boolean getMultipleCandidates() {
        return multipleCandidates;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setMultipleCandidates(Boolean multipleCandidates) {
        this.multipleCandidates = multipleCandidates;
    }

    public void setUrgentlyHiring(Boolean urgentlyHiring) {
        this.urgentlyHiring = urgentlyHiring;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public void setPerHour(Integer perHour) {
        this.perHour = perHour;
    }

    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
