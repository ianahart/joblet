package com.authentication.demo.savedjob;

import com.authentication.demo.savedjob.dto.SavedJobDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SavedJobRepository extends JpaRepository<SavedJob, Long> {
    @Query(value = """
            SELECT sj.id, sj.job_id, sj.user_id, sj.employer_id FROM saved_job sj
            WHERE sj.user_id = :userId AND sj.job_id = :jobId LIMIT 1
            """, nativeQuery = true)
    SavedJob checkIfSavedJobExists(
            @Param("jobId") Long jobId, @Param("userId") Long userId);

    @Query(value = """
               SELECT sj.id, j.id AS jobId, user_id AS userId, urgently_hiring AS urgentlyHiring,
               multiple_candidates AS multipleCandidates,
                availability, position, location, company_name AS companyName
                FROM saved_job sj
               INNER JOIN job j ON j.id = sj.job_id
               INNER JOIN employer e ON e.id = sj.employer_id
               WHERE user_id = :userId ORDER BY sj.id
            """, nativeQuery = true)
    Page<SavedJobDto> findSavedJobsByUserId(@Param("userId") Long userId, Pageable pageable);
}
