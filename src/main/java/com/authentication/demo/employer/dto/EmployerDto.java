package com.authentication.demo.employer.dto;

public class EmployerDto {
    private Long id;
    private String email;
    private String companyName;
    private String firstName;
    private String lastName;
    private String numOfEmployees;
    private String location;
    private Integer locationQuestionId;

    public EmployerDto() {
    }

    public EmployerDto(Long id, String email, String companyName, String firstName, String lastName,
            String numOfEmployees, String location, Integer locationQuestionId) {
        this.id = id;
        this.email = email;
        this.companyName = companyName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.numOfEmployees = numOfEmployees;
        this.location = location;
        this.locationQuestionId = locationQuestionId;
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setLocationQuestionId(Integer locationQuestionId) {
        this.locationQuestionId = locationQuestionId;
    }

    public void setNumOfEmployees(String numOfEmployees) {
        this.numOfEmployees = numOfEmployees;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
