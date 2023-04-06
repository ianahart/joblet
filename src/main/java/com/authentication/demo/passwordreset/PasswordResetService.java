package com.authentication.demo.passwordreset;

import com.authentication.demo.advice.ForbiddenException;
import com.authentication.demo.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetService {
    @Autowired
    private final PasswordResetRepository passwordResetRepository;

    public PasswordResetService(PasswordResetRepository passwordResetRepository) {
        this.passwordResetRepository = passwordResetRepository;
    }

    public void deleteUserPasswordResetsById(Long id) {
        this.passwordResetRepository.deleteUserPasswordResetsById(id);
    }

    public void isResetTokenValid(String token) {
        this.passwordResetRepository.findByToken(token)
                .orElseThrow(() -> new ForbiddenException("Reset token is invalid."));
    }

    public void savePasswordReset(User user, String token) {
        if (token != null && user != null) {
            PasswordReset passwordReset = new PasswordReset(token, user);
            this.passwordResetRepository.save(passwordReset);

        }
    }
}
