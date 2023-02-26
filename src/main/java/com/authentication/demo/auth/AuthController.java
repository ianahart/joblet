package com.authentication.demo.auth;

import com.authentication.demo.auth.request.LoginRequest;
import com.authentication.demo.auth.request.RefreshTokenRequest;
import com.authentication.demo.auth.request.RegisterRequest;
import com.authentication.demo.auth.response.LoginResponse;
import com.authentication.demo.auth.response.RefreshTokenResponse;
import com.authentication.demo.config.JwtService;
import com.authentication.demo.config.RefreshTokenService;
import com.authentication.demo.refreshtoken.RefreshToken;
import com.authentication.demo.token.Token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private final AuthService authService;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final RefreshTokenService refreshTokenService;

    public AuthController(
            AuthService authService,
            RefreshTokenService refreshTokenService,
            JwtService jwtService) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.authService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refresh(@RequestBody RefreshTokenRequest request) {
        RefreshToken refreshToken = this.refreshTokenService.verifyRefreshToken(request.getRefreshToken());
        this.authService.revokeAllUserTokens(refreshToken.getUser());
        String token = this.jwtService.generateToken(refreshToken.getUser());
        this.authService.saveTokenWithUser(token, refreshToken.getUser());

        return ResponseEntity.status(200).body(
                new RefreshTokenResponse(token, refreshToken.getRefreshToken()));
    }
}
