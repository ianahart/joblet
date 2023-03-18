package com.authentication.demo.employer.response;

public class GetEmployerResponse {
    private String companyName;
    private String numOfEmployees;
    private String firstName;
    private String lastName;
    private String email;
    private String location;
    private Integer locationQuestionId;

    public GetEmployerResponse() {

    }

    public GetEmployerResponse(String companyName, String numOfEmployees, String firstName,
            String lastName,
            String email, String location, Integer locationQuestionId) {
        this.companyName = companyName;
        this.numOfEmployees = numOfEmployees;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.location = location;
        this.locationQuestionId = locationQuestionId;
    }

    public String getLocation() {
        return location;
    }

    public Integer getLocationQuestionId() {
        return locationQuestionId;
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

    public String getNumOfEmployees() {
        return numOfEmployees;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLocationQuestionId(Integer locationQuestionId) {
        this.locationQuestionId = locationQuestionId;
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

}
