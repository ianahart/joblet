package com.authentication.demo.passwordreset;

import java.sql.Date;
import java.sql.Timestamp;

import com.authentication.demo.user.User;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity()
@Table(name  = "password_reset")
public class PasswordReset {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "token")
    private String token;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne()
    private User user;

    public PasswordReset() {

    }

    public PasswordReset(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public PasswordReset(Long id, Date createdAt, String token, User user) {
        this.id = id;
        this.createdAt = createdAt;
        this.token = token;
        this.user = user;
    }

    public PasswordReset(Date createdAt, String token, User user) {
        this.createdAt = createdAt;
        this.token = token;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
