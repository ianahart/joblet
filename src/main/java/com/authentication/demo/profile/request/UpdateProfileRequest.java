package com.authentication.demo.profile.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdateProfileRequest {

    @Email(message = "Please provide a valid email address.")
    private String email;
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Phone number must only consist of numbers.")
    private String phoneNumber;
    @Size(max = 100, message = "City has to be between 1 and 100 characters.")
    private String city;
    @Size(max = 2, message = "Please use two characters for state.")
    private String state;
    @Size(max = 100, message = "Country is a maximum of 100 characters.")
    private String country;
    @Size(max = 100, message = "Resume is a maximum of 100 characters.")
    private String resume;

    public UpdateProfileRequest() {

    }

    public UpdateProfileRequest(String email, String phoneNumber, String city, String state, String country,
            String resume) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.country = country;
        this.state = state;
        this.resume = resume;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public String getState() {
        return state;
    }

    public String getResume() {
        return resume;
    }

    public String getCountry() {
        return country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
