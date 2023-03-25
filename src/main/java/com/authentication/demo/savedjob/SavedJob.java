package com.authentication.demo.savedjob;

import com.authentication.demo.employer.Employer;
import com.authentication.demo.job.Job;
import com.authentication.demo.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

@Entity()
@Table(name = "saved_job")
public class SavedJob {
    @Id
    @SequenceGenerator(name = "saved_job_sequence", sequenceName = "saved_job_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "saved_job_sequence")
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne()
    private User user;

    @JoinColumn(name = "job_id", referencedColumnName = "id")
    @ManyToOne()
    private Job job;

    @JoinColumn(name = "employer_id", referencedColumnName = "id")
    @ManyToOne()
    private Employer employer;

    public SavedJob() {
    }

    public SavedJob(User user, Job job, Employer employer) {
        this.user = user;
        this.job = job;
        this.employer = employer;
    }

    public SavedJob(Long id, User user, Job job, Employer employer) {
        this.id = id;
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

    public Employer getEmployer() {
        return employer;
    }

    public User getUser() {
        return user;
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

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }
}
