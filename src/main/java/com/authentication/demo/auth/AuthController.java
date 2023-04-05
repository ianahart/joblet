package com.authentication.demo.auth;

import java.io.IOException;

import com.authentication.demo.auth.request.EmailRequest;
import com.authentication.demo.auth.request.LoginRequest;
import com.authentication.demo.auth.request.RefreshTokenRequest;
import com.authentication.demo.auth.request.RegisterRequest;
import com.authentication.demo.auth.request.PasswordResetRequest;
import com.authentication.demo.auth.response.EmailResponse;
import com.authentication.demo.auth.response.LoginResponse;
import com.authentication.demo.auth.response.PasswordResetResponse;
import com.authentication.demo.auth.response.RefreshTokenResponse;
import com.authentication.demo.config.JwtService;
import com.authentication.demo.config.RefreshTokenService;
import com.authentication.demo.email.EmailService;
import com.authentication.demo.passwordreset.PasswordResetService;
import com.authentication.demo.refreshtoken.RefreshToken;
import com.authentication.demo.user.User;
import com.authentication.demo.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private final EmailService emailService;

    @Autowired
    private final AuthService authService;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final RefreshTokenService refreshTokenService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final PasswordResetService passwordResetService;

    public AuthController(
            PasswordResetService passwordResetService,
            UserService userService,
            EmailService emailService,
            AuthService authService,
            RefreshTokenService refreshTokenService,
            JwtService jwtService) {
        this.passwordResetService = passwordResetService;
        this.userService = userService;
        this.emailService = emailService;
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
        this.jwtService = jwtService;
    }

    @PostMapping("/send-email")
    public ResponseEntity<EmailResponse> sendEmail(@RequestBody EmailRequest request)
            throws IOException, TemplateException, MessagingException {
        User user = this.userService.getUserByEmail(request.getEmail());

        this.passwordResetService.deleteUserPasswordResetsById(user.getId());
        return ResponseEntity
                .status(200)
                .body(this.emailService.sendSimpleMail(request));
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

    @PostMapping("/reset-password")
    public ResponseEntity<PasswordResetResponse> resetPassword(
            @RequestBody PasswordResetRequest request) {

        this.passwordResetService
                .isResetTokenValid(request.getToken());

        this.userService.resetUserPassword(request);

        this.passwordResetService.deleteUserPasswordResetsById(request.getId());
        return ResponseEntity.status(200).body(
                new PasswordResetResponse("Password has been reset."));
    }
}
