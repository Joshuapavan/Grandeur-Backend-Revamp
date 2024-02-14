package com.joshuapavan.grandeur.dto.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpRequest {
    private String userName;
    private String email;
    private String password;
}