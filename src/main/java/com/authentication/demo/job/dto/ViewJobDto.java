package com.authentication.demo.job.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class ViewJobDto {
    private Long id;
    private String position;
    private Integer perHour;
    private String availability;
    private Boolean urgentlyHiring;
    private Boolean multipleCandidates;
    private Timestamp createdAt;
    private String body;
    private Long employerId;
    private String companyName;
    private String location;
    private String email;
    private String firstName;
    private String lastName;
    private String numOfEmployees;
    private String readableDate;

    public ViewJobDto() {
    }

    public ViewJobDto(Long id,
            String position,
            Integer perHour,
            String body,
            Timestamp createdAt,
            Boolean multipleCandidates,
            Boolean urgentlyHiring,
            String availability,
            Long employerId,
            String companyName,
            String location,
            String email,
            String firstName,
            String lastName,
            String numOfEmployees

    ) {
        this.id = id;
        this.position = position;
        this.perHour = perHour;
        this.availability = availability;
        this.urgentlyHiring = urgentlyHiring;
        this.multipleCandidates = multipleCandidates;
        this.createdAt = createdAt;
        this.body = body;
        this.employerId = employerId;
        this.companyName = companyName;
        this.location = location;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.numOfEmployees = numOfEmployees;
    }

    public String getEmail() {
        return email;
    }

    public String getReadableDate() {
        return readableDate;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getNumOfEmployees() {
        return numOfEmployees;
    }

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getBody() {
        return body;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
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

    public Long getEmployerId() {
        return employerId;
    }

    public Boolean getUrgentlyHiring() {
        return urgentlyHiring;
    }

    public Boolean getMultipleCandidates() {
        return multipleCandidates;
    }

    public String getLocation() {
        return location;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setPerHour(Integer perHour) {
        this.perHour = perHour;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
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

    public void setPosition(String position) {
        this.position = position;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNumOfEmployees(String numOfEmployees) {
        this.numOfEmployees = numOfEmployees;
    }

    public void setReadableDate(String readableDate) {
        this.readableDate = readableDate;
    }
}
