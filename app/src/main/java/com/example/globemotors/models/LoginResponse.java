package com.example.globemotors.models;

public class LoginResponse {
    private String accessToken;
    private String message;

    // Additional fields if there are any

    // Getters and setters
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
