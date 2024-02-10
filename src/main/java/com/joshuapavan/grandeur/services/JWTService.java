package com.joshuapavan.grandeur.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    String generateToken(UserDetails userDetails);

    String getEmailFromToken(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
}
