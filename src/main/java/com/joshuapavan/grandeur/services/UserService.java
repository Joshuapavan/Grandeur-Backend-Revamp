package com.joshuapavan.grandeur.services;

import com.joshuapavan.grandeur.dto.userDto.UserResponseDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

public interface UserService {
    UserDetailsService userDetailsService();

    UserResponseDTO getUser(Long id);
}
