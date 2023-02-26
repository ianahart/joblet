package com.authentication.demo.config;

import java.time.Instant;
import java.util.UUID;

import com.authentication.demo.refreshtoken.RefreshToken;
import com.authentication.demo.refreshtoken.RefreshTokenRepository;
import com.authentication.demo.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {

    @Autowired
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private final UserRepository userRepository;

    public RefreshTokenService(
            UserRepository userRepository,
            RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshToken generateRefreshToken(Long userId) {

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(this.userRepository.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(86400000));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());

        this.refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }
}
