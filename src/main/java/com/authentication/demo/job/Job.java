package com.authentication.demo.job;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import com.authentication.demo.application.Application;
import com.authentication.demo.employer.Employer;
import com.authentication.demo.savedjob.SavedJob;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "job")
public class Job {

    @Id
    @SequenceGenerator(name = "job_sequence", sequenceName = "job_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_sequence")
    @Column(name = "id")
    private Long id;
    @Column(name = "position")
    private String position;
    @Column(name = "per_hour")
    private Integer perHour;
    @Column(name = "availability")
    private String availability;
    @Column(name = "urgently_hiring")
    private Boolean urgentlyHiring;
    @Column(name = "multiple_candidates")
    private Boolean multipleCandidates;
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "body", length = 4000)
    private String body;

    @JoinColumn(name = "employer_id", referencedColumnName = "id")
    @ManyToOne()
    private Employer employer;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<SavedJob> savedJobs;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<Application> applications;

    public Job() {
    }

    public Job(
            Long id,
            String position,
            Integer perHour,
            String availability,
            Boolean urgentlyHiring,
            Boolean multipleCandidates,
            String body,
            Employer employer

    ) {
        this.id = id;
        this.position = position;
        this.perHour = perHour;
        this.availability = availability;
        this.urgentlyHiring = urgentlyHiring;
        this.multipleCandidates = multipleCandidates;
        this.body = body;
        this.employer = employer;
    }

    public Job(
            String position,
            Integer perHour,
            String availability,
            Boolean urgentlyHiring,
            Boolean multipleCandidates,
            String body,
            Employer employer

    ) {
        this.position = position;
        this.perHour = perHour;
        this.availability = availability;
        this.urgentlyHiring = urgentlyHiring;
        this.multipleCandidates = multipleCandidates;
        this.body = body;
        this.employer = employer;
    }

    public Long getId() {
        return id;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public String getBody() {
        return body;
    }

    public List<SavedJob> getSavedJobs() {
        return savedJobs;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Integer getPerHour() {
        return perHour;
    }

    public String getPosition() {
        return position;
    }

    public Employer getEmployer() {
        return employer;
    }

    public String getAvailability() {
        return availability;
    }

    public Boolean getUrgentlyHiring() {
        return urgentlyHiring;
    }

    public Boolean getMultipleCandidates() {
        return multipleCandidates;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPerHour(Integer perHour) {
        this.perHour = perHour;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public void setUrgentlyHiring(Boolean urgentlyHiring) {
        this.urgentlyHiring = urgentlyHiring;
    }

    public void setMultipleCandidates(Boolean multipleCandidates) {
        this.multipleCandidates = multipleCandidates;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setSavedJobs(List<SavedJob> savedJobs) {
        this.savedJobs = savedJobs;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", perHour=" + perHour + '\'' +
                ", employer=" + employer +
                ", availability=" + availability +
                ", urgentlyHiring=" + urgentlyHiring +
                ", multipleCandidates=" + multipleCandidates +
                ", body=" + body +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Job job)) return false;
        return Objects.equals(id, job.id) && Objects.equals(position, job.position) && Objects.equals(perHour, job.perHour) && Objects.equals(availability, job.availability) && Objects.equals(urgentlyHiring, job.urgentlyHiring) && Objects.equals(multipleCandidates, job.multipleCandidates) && Objects.equals(createdAt, job.createdAt) && Objects.equals(updatedAt, job.updatedAt) && Objects.equals(body, job.body) && Objects.equals(employer, job.employer) && Objects.equals(savedJobs, job.savedJobs) && Objects.equals(applications, job.applications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, position, perHour, availability, urgentlyHiring, multipleCandidates, createdAt, updatedAt, body, employer, savedJobs, applications);
    }
}
