package com.authentication.demo.config;

import com.authentication.demo.advice.NotFoundException;
import com.authentication.demo.token.Token;
import com.authentication.demo.token.TokenRepository;
import com.authentication.demo.user.User;
import com.authentication.demo.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class LogoutService implements LogoutHandler {

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    public LogoutService(
            RefreshTokenService refreshTokenService,
            TokenRepository tokenRepository,
            JwtService jwtService,
            UserRepository userRepository) {
        this.refreshTokenService = refreshTokenService;
        this.tokenRepository = tokenRepository;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHeader = request.getHeader("Authorization");
        String jwt;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        jwt = authHeader.substring(7);

        var storedToken = this.tokenRepository.findByToken(jwt).orElse(null);
        User user = this.userRepository.findByEmail(this.jwtService.extractUserName(jwt))
                .orElseThrow(() -> new NotFoundException("User not found logging out."));

        storedToken.setRevoked(true);
        storedToken.setExpired(true);
        this.tokenRepository.save(storedToken);
        this.refreshTokenService.revokeAllUserRefreshTokens(user);
        SecurityContextHolder.clearContext();

    }
}
