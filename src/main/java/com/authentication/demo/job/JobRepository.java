package com.authentication.demo.job;

import java.util.List;

import com.authentication.demo.job.dto.JobDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    @Query(value = "SELECT new com.authentication.demo.job.dto.JobDto(j.id, j.position, j.perHour, j.body, j.createdAt, j.multipleCandidates, j.urgentlyHiring, j.availability, e.id, e.companyName) from Job j INNER JOIN j.employer e WHERE e.id = :employerId ORDER BY e.createdAt")
    Page<JobDto> findJobsByEmployerId(@Param("employerId") Long employerId, Pageable pageable);

}
