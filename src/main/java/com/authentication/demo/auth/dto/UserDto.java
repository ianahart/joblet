package com.authentication.demo.auth.dto;

import com.authentication.demo.user.Role;

public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private Long profileId;
    private String abbreviation;
    private Long employerId;

    public UserDto(Long id, Long profileId, String email, String firstName, String lastName, Role role,
            String abbreviation, Long employerId) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.profileId = profileId;
        this.abbreviation = abbreviation;
        this.employerId = employerId;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public Long getEmployerId() {
        return employerId;
    }

    public Long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public Long getProfileId() {
        return profileId;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
