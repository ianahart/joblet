package com.authentication.demo.review.dto;

import java.util.List;

public class ReviewPaginationDto {
    private List<ReviewDto> reviewDtos;
    private Integer totalPages;
    private Integer page;

    public ReviewPaginationDto() {
    }

    public ReviewPaginationDto(List<ReviewDto> reviewDtos, Integer totalPages, Integer page) {
        this.reviewDtos = reviewDtos;
        this.totalPages = totalPages;
        this.page = page;
    }

    public List<ReviewDto> getReviewDtos() {
        return reviewDtos;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public void setReviewDtos(List<ReviewDto> reviewDtos) {
        this.reviewDtos = reviewDtos;
    }
}
