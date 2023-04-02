package com.authentication.demo.review.dto;

public class ReviewDto {
    private String text;
    private Long id;
    private Integer rating;
    private String firstName;
    private String lastName;
    private String companyName;
    private Long userId;

    public ReviewDto(String text, Long id, Integer rating, String firstName, String lastName, String companyName,

            Long userId) {
        this.text = text;
        this.id = id;
        this.rating = rating;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getText() {
        return text;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Integer getRating() {
        return rating;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
