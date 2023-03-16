package com.authentication.demo.job.response;

public class UpdateJobResponse {
    private Long id;
    private String position;
    private Integer perHour;
    private Long employerId;
    private String availability;
    private Boolean urgentlyHiring;
    private Boolean multipleCandidates;
    private String body;

    public UpdateJobResponse() {
    }

    public UpdateJobResponse(
            Long id,
            String position,
            Integer perHour,
            String availability,
            Boolean urgentlyHiring,
            Boolean multipleCandidates,
            String body) {
        this.id = id;
        this.position = position;
        this.perHour = perHour;
        this.availability = availability;
        this.urgentlyHiring = urgentlyHiring;
        this.multipleCandidates = multipleCandidates;
        this.body = body;

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

    public Boolean getMultipleCandidates() {
        return multipleCandidates;
    }

    public Boolean getUrgentlyHiring() {
        return urgentlyHiring;
    }

    public String getAvailability() {
        return availability;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setPerHour(Integer perHour) {
        this.perHour = perHour;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public void setUrgentlyHiring(Boolean urgentlyHiring) {
        this.urgentlyHiring = urgentlyHiring;
    }

    public void setMultipleCandidates(Boolean multipleCandidates) {
        this.multipleCandidates = multipleCandidates;
    }

}
