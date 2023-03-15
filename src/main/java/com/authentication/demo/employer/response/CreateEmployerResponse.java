package com.authentication.demo.employer.response;

import java.sql.Date;

public class CreateEmployerResponse {

    private String companyName;
    private Integer numOfEmployees;
    private String firstName;
    private String lastName;
    private String email;
    private Date createdAt;

    public CreateEmployerResponse() {

    }

    public CreateEmployerResponse(String companyName, Integer numOfEmployees, String firstName, String lastName,
            String email, Date createdAt) {
        this.companyName = companyName;
        this.numOfEmployees = numOfEmployees;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Integer getNumOfEmployees() {
        return numOfEmployees;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setNumOfEmployees(Integer numOfEmployees) {
        this.numOfEmployees = numOfEmployees;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
