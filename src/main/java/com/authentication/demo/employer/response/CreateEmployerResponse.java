package com.authentication.demo.employer.response;

import java.sql.Date;

public class CreateEmployerResponse {
    private Long id;
    private String companyName;
    private String numOfEmployees;
    private String firstName;
    private String lastName;
    private String email;
    private Date createdAt;
    private String location;

    public CreateEmployerResponse() {

    }

    public CreateEmployerResponse(Long id, String companyName, String numOfEmployees, String firstName,
            String lastName,
            String email, Date createdAt, String location) {
        this.id = id;
        this.companyName = companyName;
        this.numOfEmployees = numOfEmployees;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.createdAt = createdAt;
        this.location = location;
    }






    public String getLocation() {
        return location;
    }

    public Long getId() {
        return id;
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

    public String getNumOfEmployees() {
        return numOfEmployees;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public void setNumOfEmployees(String numOfEmployees) {
        this.numOfEmployees = numOfEmployees;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
