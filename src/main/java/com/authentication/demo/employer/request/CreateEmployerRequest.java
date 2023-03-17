package com.authentication.demo.employer.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateEmployerRequest {

    @Size(max = 175, message = "Pleae keep company name under 175 characters.")
    private String companyName;
    @Size(max = 175, message = "Pleae keep first name under 175 characters.")
    private String numOfEmployees;
    @Size(max = 175, message = "Pleae keep first name under 175 characters.")
    private String firstName;
    @Size(max = 175, message = "Pleae keep last name under 175 characters.")
    private String lastName;
    @Email(message = "Please provide a valid email address.")
    private String email;
    @Size(max = 175, message = "Pleae keep location under 175 characters.")
    private String location;

    public CreateEmployerRequest() {

    }

    public CreateEmployerRequest(String companyName, String numOfEmployees, String firstName, String lastName,
            String email, String location) {
        this.companyName = companyName;
        this.numOfEmployees = numOfEmployees;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.location = location;
    }

    public String getLocation() {
        return location;

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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setNumOfEmployees(String numOfEmployees) {
        this.numOfEmployees = numOfEmployees;
    }
}
