package com.authentication.demo.search.response;

import com.authentication.demo.search.Search;
import com.authentication.demo.search.dto.SearchDto;

import java.util.List;

public class GetSearchesResponse {
    private List<SearchDto> searches;

    public GetSearchesResponse() {
    }

    public GetSearchesResponse(List<SearchDto> searches) {
        this.searches = searches;
    }

    public List<SearchDto> getSearches() {
        return searches;
    }

    public void setSearches(List<SearchDto> searches) {
        this.searches = searches;
    }
}
