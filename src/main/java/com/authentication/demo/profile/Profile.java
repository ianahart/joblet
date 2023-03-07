package com.authentication.demo.profile;

import com.authentication.demo.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "country")
    private String country;
    @Column(name = "resume")
    private String resume;

    @JsonIgnore
    @OneToOne(mappedBy = "profile")
    private User user;

    public Profile() {

    }

    public Profile(User user) {
        this.user = user;
    }

    public Profile(Long id, String email, String phoneNumber, String city, String state, String country,
            String resume) {
    }

    public Profile(String email, String phoneNumber, String city, String state, String country,
            String resume) {

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

    public User getUser() {
        return user;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber + '\'' +
                ", city=" + city +
                ", state=" + state +
                ", country=" + country +
                ", resume=" + resume +
                '}';
    }

}
