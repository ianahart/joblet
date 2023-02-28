package com.authentication.demo.auth.request;

public class PasswordResetRequest {
    private Long id;
    private String token;
    private String confirmPassword;
    private String newPassword;

    public PasswordResetRequest() {

    }

    public PasswordResetRequest(Long id, String token, String confirmPassword, String newPassword) {
        this.id = id;
        this.token = token;
        this.confirmPassword = confirmPassword;
        this.newPassword = newPassword;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
