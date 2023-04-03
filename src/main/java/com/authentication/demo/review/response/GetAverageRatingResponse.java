package com.authentication.demo.review.response;

public class GetAverageRatingResponse {
    private Integer avgRating;

    public GetAverageRatingResponse() {
    }

    public GetAverageRatingResponse(Integer avgRating) {
        this.avgRating = avgRating;
    }

    public Integer getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Integer avgRating) {
        this.avgRating = avgRating;
    }
}
