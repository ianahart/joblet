package com.authentication.demo.auth.response;

import com.authentication.demo.auth.dto.UserDto;

public class LoginResponse {
    private String token;
    private String refreshToken;
    private UserDto userDto;

    public String getToken() {
        return token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public LoginResponse(String token, String refreshToken, UserDto userDto) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.userDto = userDto;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
