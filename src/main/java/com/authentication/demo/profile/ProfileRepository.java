package com.authentication.demo.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query(value = """
               SELECT * FROM profile 
               WHERE resume IS NULL
            """, nativeQuery = true)
    Profile checkForNullProfile(@Param("profileId") Long profileId);

}
