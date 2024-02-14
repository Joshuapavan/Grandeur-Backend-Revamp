package com.joshuapavan.grandeur.services;

import com.joshuapavan.grandeur.dto.auth.LoginRequest;
import com.joshuapavan.grandeur.dto.auth.SignUpRequest;
import com.joshuapavan.grandeur.dto.auth.TokenRequest;
import com.joshuapavan.grandeur.dto.auth.TokenResponse;

public interface AuthenticationService {

    TokenResponse signUp(SignUpRequest signUpRequest);
    TokenResponse login(LoginRequest loginRequest);

    TokenResponse generateTokenFromResponseToken(TokenRequest tokenRequest);
}
