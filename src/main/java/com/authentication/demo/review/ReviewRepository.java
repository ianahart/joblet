package com.authentication.demo.review;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = """

             SELECT * FROM review r
             WHERE r.employer_id = :employerId
            AND r.user_id = :userId
             """, nativeQuery = true)

    List<Review> getReviewByUserAndEmployer(@Param("userId") Long userId, @Param("employerId") Long employerId);

}
