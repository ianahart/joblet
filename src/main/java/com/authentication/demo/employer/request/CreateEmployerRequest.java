package com.authentication.demo.employer.request;

public class CreateEmployerRequest {

    private String companyName;
    private Integer numOfEmployees;
    private String firstName;
    private String lastName;
    private String email;

    public CreateEmployerRequest() {

    }

    public CreateEmployerRequest(String companyName, Integer numOfEmployees, String firstName, String lastName,
            String email) {
        this.companyName = companyName;
        this.numOfEmployees = numOfEmployees;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getEmail() {
        return email;
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
}
