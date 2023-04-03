package com.authentication.demo.review;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.authentication.demo.review.dto.ReviewDto;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = """
              SELECT AVG(r.rating) FROM review r WHERE r.employer_id = :employerId

            """, nativeQuery = true)
    Integer getAvgReviewRating(@Param("employerId") Long employerId);

    @Query(value = """
            SELECT new com.authentication.demo.review.dto.ReviewDto(
            r.text, r.id, r.rating, u.firstName, u.lastName, e.companyName, u.id as userId)
            FROM Review r
            INNER JOIN r.user u
            INNER JOIN r.employer e
            ORDER BY r.id
                    """)
    Page<ReviewDto> getReviews(Pageable pageable);

    @Query(value = """
             SELECT * FROM review r
             WHERE r.employer_id = :employerId
            AND r.user_id = :userId
             """, nativeQuery = true)

    List<Review> getReviewByUserAndEmployer(@Param("userId") Long userId, @Param("employerId") Long employerId);

}
