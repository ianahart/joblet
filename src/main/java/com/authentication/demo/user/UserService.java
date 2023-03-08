package com.authentication.demo.user;

import java.security.Key;

import com.authentication.demo.advice.BadRequestException;
import com.authentication.demo.advice.ForbiddenException;
import com.authentication.demo.advice.NotFoundException;
import com.authentication.demo.auth.dto.UserDto;
import com.authentication.demo.auth.request.PasswordResetRequest;
import com.authentication.demo.config.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class UserService {

    @Value("${secretkey}")
    private String secretKey;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JwtService jwtService;

    public UserService(
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractUserIdFromToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    public UserDto getUserByToken(String token) {
        Claims claims = extractUserIdFromToken(token);

        User user = getUserByEmail(claims.getSubject());
        UserDto userDto = new UserDto(
                user.getId(),
                user.getProfile().getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                user.getAbbreviation());
        return userDto;

    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public void resetUserPassword(PasswordResetRequest request) {

        User user = this.userRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException("User not found."));

        if (!this.jwtService.isTokenValid(request.getToken(), user)) {
            throw new ForbiddenException("Link has expired.");
        }

        if (!this.passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new BadRequestException("Old password is incorrect.");
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("Passwords do not match.");
        }
        user.setPassword(this.passwordEncoder.encode(request.getNewPassword()));
        this.userRepository.save(user);
    }
}
