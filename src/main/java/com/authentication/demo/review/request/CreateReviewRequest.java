package com.authentication.demo.review.request;

public class CreateReviewRequest {

    private Integer rating;
    private String text;
    private Long employerId;
    private Long userId;

    public CreateReviewRequest() {
    }

    public CreateReviewRequest(Integer rating, String text, Long employerId, Long userId) {
        this.rating = rating;
        this.text = text;
        this.employerId = employerId;
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public Long getUserId() {
        return userId;
    }

    public Integer getRating() {
        return rating;
    }

    public Long getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setText(String text) {
        this.text = text;
    }
}
