package com.authentication.demo.auth;

import com.authentication.demo.auth.request.LoginRequest;
import com.authentication.demo.auth.request.RegisterRequest;
import com.authentication.demo.auth.response.LoginResponse;
import com.authentication.demo.config.JwtService;
import com.authentication.demo.user.Role;
import com.authentication.demo.user.User;
import com.authentication.demo.user.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            JwtService jwtService,
            AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public RegisterResponse register(RegisterRequest request) {
        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                this.passwordEncoder.encode(request.getPassword()),
                Role.USER);

        this.userRepository.save(user);
        return new RegisterResponse("User created.");
    }

    public LoginResponse login(LoginRequest request) {

        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));
        User user = this.userRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = this.jwtService.generateToken(user);
    }
}
