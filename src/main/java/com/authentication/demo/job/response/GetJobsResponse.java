package com.authentication.demo.job.response;

import java.util.List;

import com.authentication.demo.job.Job;
import com.authentication.demo.job.dto.JobDto;

public class GetJobsResponse {
    private Integer page;
    private Integer size;
    private List<JobDto> jobs;
    private Integer totalPages;

    public GetJobsResponse() {
    }

    public GetJobsResponse(Integer page, Integer size, List<JobDto> jobs, Integer totalPages) {
        this.page = page;
        this.size = size;
        this.jobs = jobs;
        this.totalPages = totalPages;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Integer getSize() {
        return size;
    }

    public List<JobDto> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobDto> jobs) {
        this.jobs = jobs;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
