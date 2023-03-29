package com.authentication.demo.application;

import com.authentication.demo.employer.Employer;
import com.authentication.demo.job.Job;
import com.authentication.demo.profile.Profile;
import com.authentication.demo.user.User;

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

    public Application() {
    }

    public Application(Profile profile, User user, Job job, Employer employer) {
        this.profile = profile;
        this.user = user;
        this.job = job;
        this.employer = employer;
    }

    public Application(Long id, Profile profile, User user, Job job, Employer employer) {
        this.id = id;
        this.profile = profile;
        this.user = user;
        this.job = job;
        this.employer = employer;
    }

    public Long getId() {
        return id;
    }

    public Job getJob() {
        return job;
    }

    public User getUser() {
        return user;
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

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }
}
