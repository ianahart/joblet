package com.authentication.demo.search;

import com.authentication.demo.search.response.DeleteSearchResponse;
import com.authentication.demo.search.response.GetSearchesResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/searches")
public class SearchController {

    @Autowired
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteSearchResponse> deleteSearch(@PathVariable("id") Long id) {
        this.searchService.deleteSearch(id);
        return ResponseEntity.status(HttpStatus.OK).body(new DeleteSearchResponse("Success"));
    }

    @GetMapping("/")
    public ResponseEntity<GetSearchesResponse> getSearches(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new GetSearchesResponse(this.searchService.getSearches(request)));
    }
}
