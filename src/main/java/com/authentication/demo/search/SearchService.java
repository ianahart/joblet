package com.authentication.demo.search;

import com.authentication.demo.user.User;
import com.authentication.demo.user.UserRepository;
import com.authentication.demo.advice.NotFoundException;
import com.authentication.demo.search.dto.SearchDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class SearchService {
    @Autowired
    private final SearchRepository searchRepository;

    @Autowired
    private final UserRepository userRepository;

    public SearchService(SearchRepository searchRepository, UserRepository userRepository) {
        this.searchRepository = searchRepository;
        this.userRepository = userRepository;
    }

    public void deleteSearch(Long id) {
        this.searchRepository.deleteById(id);
    }

    public List<SearchDto> getSearches(HttpServletRequest request) {

        String userName = "";
        Object auth = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (auth instanceof UserDetails) {
            userName = ((UserDetails) auth).getUsername();
        }

        User user = this.userRepository
                .findByEmail(userName)
                .orElseThrow(() -> new NotFoundException("User not found."));

        return this.searchRepository.getLatestSearches(user.getId());

    }

    public void createSearch(User user, String term) {
        if (this.searchRepository.checkForDuplicateTerms(user.getId(), term).size() < 1) {
            Search search = new Search();
            search.setUser(user);
            search.setTerm(term);

            this.searchRepository.save(search);

        }

    }
}
