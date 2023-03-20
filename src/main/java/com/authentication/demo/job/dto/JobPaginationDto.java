package com.authentication.demo.job.dto;

import java.util.List;

public class JobPaginationDto {
    private List<JobDto> jobDto;
    private Integer totalPages;
    private Integer page;

    public JobPaginationDto() {
    }

    public JobPaginationDto(List<JobDto> jobDto, Integer totalPages, Integer page) {
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

    public List<JobDto> getJobDto() {
        return jobDto;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setJobDto(List<JobDto> jobDto) {
        this.jobDto = jobDto;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
