package com.authentication.demo.job;

import java.sql.Date;

import com.authentication.demo.employer.Employer;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue
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
    private Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "body", length = 4000)
    private String body;

    @JoinColumn(name = "employer_id", referencedColumnName = "id")
    @ManyToOne()
    private Employer employer;

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

    public String getBody() {
        return body;
    }

    public Date getCreatedAt() {
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

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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
}