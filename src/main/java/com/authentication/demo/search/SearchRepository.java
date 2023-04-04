package com.authentication.demo.search;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.authentication.demo.search.dto.SearchDto;

@Repository
public interface SearchRepository extends JpaRepository<Search, Long> {

    @Query(value = """
              SELECT * FROM search s
              WHERE s.user_id = :userId
              AND s.term = :term
            """, nativeQuery = true)
    List<Search> checkForDuplicateTerms(@Param("userId") Long userId, @Param("term") String term);

    @Query(value = """
              SELECT s.id, s.user_id AS userId, s.term FROM search s
              WHERE s.user_id = :id
              ORDER BY s.created_at
              DESC LIMIT 10
            """, nativeQuery = true)
    List<SearchDto> getLatestSearches(@Param("id") Long id);

}
