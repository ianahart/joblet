package com.authentication.demo.savedjob;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SavedJobRepository extends JpaRepository<SavedJob, Long> {

    @Query(value = """
            SELECT j.id, j.job_id, j.user_id FROM saved_job j
            WHERE j.user_id = :userId AND j.job_id = :jobId LIMIT 1
            """, nativeQuery = true)
    SavedJob checkIfSavedJobExists(
            @Param("jobId") Long jobId, @Param("userId") Long userId);

}
