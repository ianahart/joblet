package com.authentication.demo.auth;

import java.util.List;
import java.util.Optional;

import com.authentication.demo.advice.BadRequestException;
import com.authentication.demo.advice.ForbiddenException;
import com.authentication.demo.advice.NotFoundException;
import com.authentication.demo.auth.dto.UserDto;
import com.authentication.demo.auth.request.LoginRequest;
import com.authentication.demo.auth.request.RegisterRequest;
import com.authentication.demo.auth.response.LoginResponse;
import com.authentication.demo.config.JwtService;
import com.authentication.demo.config.RefreshTokenService;
import com.authentication.demo.profile.Profile;
import com.authentication.demo.profile.ProfileService;
import com.authentication.demo.refreshtoken.RefreshToken;
import com.authentication.demo.token.Token;
import com.authentication.demo.token.TokenRepository;
import com.authentication.demo.token.TokenType;
import com.authentication.demo.user.Role;
import com.authentication.demo.user.User;
import com.authentication.demo.user.UserRepository;
import com.authentication.demo.util.MyUtils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ProfileService profileService;
    private final RefreshTokenService refreshTokenService;

    public AuthService(
            ProfileService profileService,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            TokenRepository tokenRepository,
            JwtService jwtService,
            RefreshTokenService refreshTokenService,
            AuthenticationManager authenticationManager) {
        this.profileService = profileService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.authenticationManager = authenticationManager;
    }

    public RegisterResponse register(RegisterRequest request) {
        Profile profile = this.profileService.createProfile();
        User user = new User(
                MyUtils.capitalize(request.getFirstName()),
                profile,
                MyUtils.capitalize(request.getLastName()),
                request.getEmail(),
                this.passwordEncoder.encode(request.getPassword()),
                request.getRole().equals("USER") ? Role.USER : Role.EMPLOYER);

        Optional<User> exists = this.userRepository.findByEmail(request.getEmail());

        if (exists.isPresent()) {
            throw new BadRequestException("A user with that email already exists.");
        }
        this.userRepository.save(user);

        return new RegisterResponse("User created.");
    }

    public void saveTokenWithUser(String token, User user) {
        Token tokenToSave = new Token(token, TokenType.BEARER, false, false, user);
        this.tokenRepository.save(tokenToSave);

    }

    public void revokeAllUserTokens(User user) {
        List<Token> tokens = this.tokenRepository.findAllValidTokens(user.getId());

        if (tokens.isEmpty()) {
            return;
        }

        tokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });

        this.tokenRepository.saveAll(tokens);
    }

    public LoginResponse login(LoginRequest request) {

        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()));

        } catch (BadCredentialsException e) {
            throw new ForbiddenException("Credentials are invalid");
        }
        User user = this.userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found by email."));
        String jwtToken = this.jwtService.generateToken(user);

        this.revokeAllUserTokens(user);
        this.saveTokenWithUser(jwtToken, user);
        RefreshToken refreshToken = this.refreshTokenService.generateRefreshToken(user.getId());

        UserDto userDto = new UserDto(
                user.getId(),
                user.getProfile().getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                user.getAbbreviation());
        return new LoginResponse(jwtToken, refreshToken.getRefreshToken(), userDto);
    }
}
