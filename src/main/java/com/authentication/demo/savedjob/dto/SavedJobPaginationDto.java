package com.authentication.demo.savedjob.dto;

import java.util.List;

public class SavedJobPaginationDto {
    private List<SavedJobDto> jobDto;
    private Integer totalPages;
    private Integer page;

    public SavedJobPaginationDto() {
    }

    public SavedJobPaginationDto(List<SavedJobDto> jobDto, Integer totalPages, Integer page) {
        this.jobDto = jobDto;
        this.totalPages = totalPages;
        this.page = page;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public List<SavedJobDto> getJobDto() {
        return jobDto;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setSavedJobDto(List<SavedJobDto> jobDto) {
        this.jobDto = jobDto;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
