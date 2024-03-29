package com.authentication.demo.employer;

import com.authentication.demo.application.Application;
import com.authentication.demo.job.Job;
import com.authentication.demo.review.Review;
import com.authentication.demo.user.User;
import com.authentication.demo.savedjob.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "employer")
public class Employer {

    @Id
    @SequenceGenerator(name = "employer_sequence", sequenceName = "employer_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employer_sequence")
    @Column(name = "id")
    private Long id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "num_of_employees")
    private String numOfEmployees;
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
    @Column(name = "location_question_id")
    private Integer locationQuestionId;

    @JsonIgnore
    @OneToOne(mappedBy = "employer")
    private User user;

    @OneToMany(mappedBy = "employer")
    private List<Application> applications;

    @OneToMany(mappedBy = "employer")
    private List<Job> jobs;

    @OneToMany(mappedBy = "employer")
    private List<SavedJob> savedJobs;

    @OneToMany(mappedBy = "employer")
    private List<Review> reviews;

    public Employer(User user) {
        this.user = user;
    }

    public Employer() {

    }

    public Employer(Long id, String email, String companyName, String firstName, String lastName,
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

    public Employer(String email, String companyName, String firstName, String lastName,
            String numOfEmployees, String location, Integer locationQuestionId) {
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

    public List<Review> getReviews() {
        return reviews;
    }

    public String getLocation() {
        return location;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public List<SavedJob> getSavedJobs() {
        return savedJobs;
    }

    public Integer getLocationQuestionId() {
        return locationQuestionId;
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

    public List<Job> getJobs() {
        return jobs;
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public void setSavedJobs(List<SavedJob> savedJobs) {
        this.savedJobs = savedJobs;
    }

    public void setLocationQuestionId(Integer locationQuestionId) {
        this.locationQuestionId = locationQuestionId;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employer employer)) return false;
        return Objects.equals(id, employer.id) && Objects.equals(companyName, employer.companyName) && Objects.equals(numOfEmployees, employer.numOfEmployees) && Objects.equals(firstName, employer.firstName) && Objects.equals(lastName, employer.lastName) && Objects.equals(email, employer.email) && Objects.equals(createdAt, employer.createdAt) && Objects.equals(updatedAt, employer.updatedAt) && Objects.equals(location, employer.location) && Objects.equals(locationQuestionId, employer.locationQuestionId) && Objects.equals(user, employer.user) && Objects.equals(applications, employer.applications) && Objects.equals(jobs, employer.jobs) && Objects.equals(savedJobs, employer.savedJobs) && Objects.equals(reviews, employer.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, numOfEmployees, firstName, lastName, email, createdAt, updatedAt, location, locationQuestionId, user, applications, jobs, savedJobs, reviews);
    }
}
