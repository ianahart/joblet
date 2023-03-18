package com.authentication.demo.employer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {

    @Query(value = """
                SELECT * FROM
                employer e
                WHERE e.id = :id
                ORDER BY created_at DESC LIMIT 1;
            """, nativeQuery = true)
    Employer getLatestEmployer(@Param("id") Long id);
}
