package com.authentication.demo.savedjob.response;

import java.util.List;

import com.authentication.demo.savedjob.dto.SavedJobDto;

public class GetSavedJobsResponse {
    private Integer page;
    private Integer size;
    private List<SavedJobDto> jobs;
    private Integer totalPages;

    public GetSavedJobsResponse() {
    }

    public GetSavedJobsResponse(Integer page, Integer size, List<SavedJobDto> jobs, Integer totalPages) {
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

    public List<SavedJobDto> getJobs() {
        return jobs;
    }

    public void setJobs(List<SavedJobDto> jobs) {
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
