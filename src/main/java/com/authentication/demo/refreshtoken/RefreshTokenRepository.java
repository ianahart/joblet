package com.authentication.demo.refreshtoken;

import java.util.Optional;

import com.authentication.demo.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByRefreshToken(String token);

    @Modifying
    int deleteByUser(User user);
}
