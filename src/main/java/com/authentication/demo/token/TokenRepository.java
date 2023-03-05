package com.authentication.demo.token;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(value = """
                     SELECT expired, t.id, revoked, token, token_type, user_id
                     FROM token t
                     WHERE t.user_id = :userId
            """, nativeQuery = true)

    List<Token> checkForDuplicateTokens(@Param("userId") Long userId);

    @Query(value = """
             SELECT expired, t.id, revoked, token, token_type, user_id
             FROM token t
             INNER JOIN _user u
             ON t.user_id = u.id
             WHERE T.user_id = :id
             AND t.expired = false
            OR t.revoked = false
                            """, nativeQuery = true)
    List<Token> findAllValidTokens(@Param("id") Long id);

    Optional<Token> findByToken(@Param("token") String token);

    @Modifying
    @Query(value = """
               DELETE FROM token t
               WHERE t.token = :token
            """, nativeQuery = true)
    @Transactional
    void deleteByToken(@Param("token") String token);

}
