package com.joshuapavan.grandeur.controllers;

import com.joshuapavan.grandeur.dto.auth.LoginRequest;
import com.joshuapavan.grandeur.dto.auth.SignUpRequest;
import com.joshuapavan.grandeur.dto.auth.TokenRequest;
import com.joshuapavan.grandeur.dto.auth.TokenResponse;
import com.joshuapavan.grandeur.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @GetMapping("/test")
    public String test(){
        return "Success";
    }

    @PostMapping("/signUp")
    public ResponseEntity<TokenResponse> signUp(@RequestBody SignUpRequest signUpRequest){
        return new ResponseEntity<>(authenticationService.signUp(signUpRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest){
        return new ResponseEntity<>(authenticationService.login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/getToken")
    public ResponseEntity<TokenResponse> getToken(@RequestBody TokenRequest tokenRequest){
        return new ResponseEntity<>(authenticationService.generateTokenFromResponseToken(tokenRequest), HttpStatus.OK);
    }
}
