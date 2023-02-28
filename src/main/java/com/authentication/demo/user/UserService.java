package com.authentication.demo.user;

import com.authentication.demo.advice.BadRequestException;
import com.authentication.demo.advice.ForbiddenException;
import com.authentication.demo.advice.NotFoundException;
import com.authentication.demo.auth.request.PasswordResetRequest;
import com.authentication.demo.config.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
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

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("Passwords do not match.");
        }
        user.setPassword(this.passwordEncoder.encode(request.getNewPassword()));
        this.userRepository.save(user);
    }
}
