package com.authentication.demo.application;

import java.sql.Timestamp;
import java.util.Objects;

import com.authentication.demo.employer.Employer;
import com.authentication.demo.job.Job;
import com.authentication.demo.profile.Profile;
import com.authentication.demo.user.User;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "application")
public class Application {
    @Id
    @SequenceGenerator(name = "application_sequence", sequenceName = "application_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "application_sequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "job_company")
    private String jobCompany;

    @Column(name = "job_position")
    private String jobPosition;

    @OneToOne()
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private Job job;

    @ManyToOne()
    @JoinColumn(name = "employer_id", referencedColumnName = "id")
    private Employer employer;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    public Application() {
    }

    public Application(Profile profile, User user, Job job, Employer employer, String jobCompany, String jobPosition) {
        this.profile = profile;
        this.user = user;
        this.job = job;
        this.employer = employer;
        this.jobCompany = jobCompany;
        this.jobPosition = jobPosition;
    }

    public Application(Long id, Profile profile, User user, Job job, Employer employer, String jobCompany,
            String jobPosition) {
        this.id = id;
        this.profile = profile;
        this.user = user;
        this.job = job;
        this.employer = employer;
        this.jobCompany = jobCompany;
        this.jobPosition = jobPosition;
    }

    public Long getId() {
        return id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Job getJob() {
        return job;
    }

    public User getUser() {
        return user;
    }

    public String getJobCompany() {
        return jobCompany;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public Profile getProfile() {
        return profile;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setJobCompany(String jobCompany) {
        this.jobCompany = jobCompany;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Application that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(jobCompany, that.jobCompany) && Objects.equals(jobPosition, that.jobPosition) && Objects.equals(profile, that.profile) && Objects.equals(user, that.user) && Objects.equals(job, that.job) && Objects.equals(employer, that.employer) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jobCompany, jobPosition, profile, user, job, employer, createdAt);
    }
}
