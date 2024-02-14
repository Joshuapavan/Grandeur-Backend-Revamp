package com.joshuapavan.grandeur.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

public interface UserService {
    UserDetailsService userDetailsService();
}
