package com.example.globemotors.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;

public class JWTUtils {

    public static int getUserIdFromToken(String token) {
        try {
            return JWT.decode(token).getClaim("user").asInt();
        } catch (JWTDecodeException e) {
            e.printStackTrace();
            return -1; // Return -1 or handle the exception as needed
        }
    }
}
