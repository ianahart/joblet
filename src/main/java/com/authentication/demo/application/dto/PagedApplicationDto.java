package com.authentication.demo.application.dto;

import com.authentication.demo.application.Application;
import java.util.List;

public class PagedApplicationDto {
    private List<ApplicationDto> applications;
    private Integer totalPages;
    private Integer page;

    public PagedApplicationDto() {
    }

    public PagedApplicationDto(List<ApplicationDto> applications, Integer totalPages, Integer page) {
        this.applications = applications;
        this.totalPages = totalPages;
        this.page = page;
    }

    public List<ApplicationDto> getApplications() {
        return applications;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setApplications(List<ApplicationDto> applications) {
        this.applications = applications;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
