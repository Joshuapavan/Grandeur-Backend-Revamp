package com.joshuapavan.grandeur.services;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTService {
    String generateToken(UserDetails userDetails);
    String generateToken(Map<String,Object> extraClaims, UserDetails userDetails);
    String getEmailFromToken(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
}
