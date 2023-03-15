package com.authentication.demo.employer;

import com.authentication.demo.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employer")
public class Employer {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "num_of_employees")
    private Integer numOfEmployees;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;
    @CreationTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "location")
    private String location;

    @JsonIgnore
    @OneToOne(mappedBy = "employer")
    private User user;

    public Employer(User user) {
        this.user = user;
    }

    public Employer() {

    }

    public Employer(Long id, String email, String companyName, String firstName, String lastName,
            Integer numOfEmployees, String location) {
        this.id = id;
        this.email = email;
        this.companyName = companyName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.numOfEmployees = numOfEmployees;
        this.location = location;
    }

    public Employer(String email, String companyName, String firstName, String lastName,
            Integer numOfEmployees, String location) {
        this.email = email;
        this.companyName = companyName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.numOfEmployees = numOfEmployees;
        this.location = location;

    }

    public Long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdated_at() {
        return updatedAt;
    }

    public User getUser() {
        return user;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setNumOfEmployees(Integer numOfEmployees) {
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

    @Override
    public String toString() {
        return "Employer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", companyName=" + companyName + '\'' +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", numOfEmployees=" + numOfEmployees +
                ", location=" + location +
                '}';
    }

}
