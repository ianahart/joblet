package com.authentication.demo.profile.response;

import com.authentication.demo.profile.Profile;

public class ProfileResponse {

    private String city;
    private String country;
    private String email;
    private String phoneNumber;
    private String resume;
    private String state;
    private Long id;
    private String fileName;

    public ProfileResponse() {

    }

    public ProfileResponse(

            String city,
            String country,
            String email,
            String phoneNumber,
            String resume,
            String state,
            Long id,
            String fileName

    ) {
        this.city = city;
        this.country = country;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.resume = resume;
        this.state = state;
        this.id = id;
        this.fileName = fileName;
    }

    public Long getId() {
        return id;
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

    public String getFileName() {
        return fileName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
