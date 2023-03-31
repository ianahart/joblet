package com.authentication.demo.application;

import com.authentication.demo.application.dto.ApplicationDetailsDto;
import com.authentication.demo.application.dto.ApplicationDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(value = """
              SELECT a.id AS id, created_at AS createdAt, job_company AS jobCompany,
              job_position AS jobPosition, employer_id AS employerId, profile_id AS profileId,
              user_id AS userId, job_id AS jobId
              FROM application a
              WHERE a.employer_id = :employerId
            """, nativeQuery = true)
    Page<ApplicationDto> findApplications(@Param("employerId") Long employerId, Pageable pageable);

    @Query(value = """
            SELECT first_name AS firstName, last_name AS lastName,
             p.email, phone_number AS phoneNumber, resume, city,
            state, country
            FROM application a
            INNER JOIN _user u ON u.id = :userId
            INNER JOIN profile p ON p.id = :profileId
            WHERE a.id = :id
            """, nativeQuery = true)
    ApplicationDetailsDto getApplication(@Param("id") Long id,
            @Param("profileId") Long profileId, @Param("userId") Long userId);

}
