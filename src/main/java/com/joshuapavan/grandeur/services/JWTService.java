package com.joshuapavan.grandeur.services;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTService {
    String generateToken(UserDetails userDetails);
    String generateRefreshToken(UserDetails userDetails);
    String generateToken(Map<String,Object> extraClaims, UserDetails userDetails);
    boolean isTokenValid(String token,UserDetails userDetails);
    String extractEmail(String token);
}
