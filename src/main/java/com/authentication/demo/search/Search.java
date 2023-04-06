package com.authentication.demo.search;

import java.sql.Timestamp;
import java.util.Objects;

import com.authentication.demo.user.User;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "search")
public class Search {

    @Id
    @SequenceGenerator(name = "search_sequence", sequenceName = "search_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "search_sequence")
    @Column(name = "id")
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "term")
    private String term;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne()
    private User user;


    public Search() {}

    public Search(String term, User user) {
        this.term = term;
        this.user = user;
    }

    public Search(Long id, String term, User user) {
        this.id = id;
        this.term = term;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getTerm() {
        return term;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Search search)) return false;
        return Objects.equals(id, search.id) && Objects.equals(createdAt, search.createdAt) && Objects.equals(term, search.term) && Objects.equals(user, search.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, term, user);
    }
}
