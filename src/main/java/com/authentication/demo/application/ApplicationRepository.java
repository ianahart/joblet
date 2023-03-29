package com.authentication.demo.application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query(value = """
            SELECT * FROM application a
            WHERE a.job_id = :jobId AND a.user_id = :userId
                """, nativeQuery = true)
    Application getApplicationByUserIdAndJobId(@Param("jobId") Long jobId, @Param("userId") Long userId);

}
