package com.joshuapavan.grandeur.controllers;

import com.joshuapavan.grandeur.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;

@RequiredArgsConstructor
public class BaseController {
    
    private final UserDetailsService userDetailsService;
    public User getUser() {
        User user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            user = (User) userDetailsService.loadUserByUsername(username);
        }
        return user;
    }
}
