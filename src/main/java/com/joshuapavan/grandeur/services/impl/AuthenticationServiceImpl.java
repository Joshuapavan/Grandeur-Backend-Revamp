package com.joshuapavan.grandeur.services.impl;

import com.joshuapavan.grandeur.dto.auth.LoginRequest;
import com.joshuapavan.grandeur.dto.auth.SignUpRequest;
import com.joshuapavan.grandeur.dto.auth.TokenRequest;
import com.joshuapavan.grandeur.dto.auth.TokenResponse;
import com.joshuapavan.grandeur.enums.Role;
import com.joshuapavan.grandeur.models.User;
import com.joshuapavan.grandeur.repositories.UserRepo;
import com.joshuapavan.grandeur.services.AuthenticationService;
import com.joshuapavan.grandeur.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public TokenResponse signUp(SignUpRequest signUpRequest) {
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setUserName(signUpRequest.getUserName());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.USER);

        userRepo.save(user);
        String jwt = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new TokenResponse(jwt, refreshToken);
    }

    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        User user = userRepo.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with email %s not found",loginRequest.getEmail())
                ));

        String jwt = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new TokenResponse(jwt,refreshToken);
    }

    @Override
    public TokenResponse generateTokenFromResponseToken(TokenRequest tokenRequest) {
        String email = jwtService.extractEmail(tokenRequest.getRefreshToken());
        User user =  userRepo.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                String.format("User with email %s not found",email))
                );
        if(jwtService.isTokenValid(tokenRequest.getRefreshToken(), user)){
            return new TokenResponse(jwtService.generateToken(user),
                    jwtService.generateRefreshToken(user));
        }
        return new TokenResponse();
    }

}
