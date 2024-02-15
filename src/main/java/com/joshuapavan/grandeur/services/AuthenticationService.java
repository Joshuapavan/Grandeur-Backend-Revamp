package com.joshuapavan.grandeur.services;

import com.joshuapavan.grandeur.dto.authDto.LoginRequest;
import com.joshuapavan.grandeur.dto.authDto.SignUpRequest;
import com.joshuapavan.grandeur.dto.authDto.TokenRequest;
import com.joshuapavan.grandeur.dto.authDto.TokenResponse;

public interface AuthenticationService {

    TokenResponse signUp(SignUpRequest signUpRequest);
    TokenResponse login(LoginRequest loginRequest);

    TokenResponse generateTokenFromResponseToken(TokenRequest tokenRequest);
}
