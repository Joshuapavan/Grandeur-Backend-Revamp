package com.joshuapavan.grandeur.services.impl;

import com.joshuapavan.grandeur.dto.userDto.UserResponseDTO;
import com.joshuapavan.grandeur.models.User;
import com.joshuapavan.grandeur.repositories.UserRepo;
import com.joshuapavan.grandeur.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    private final String baseUrl = "http://localhost:8080/";

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepo.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException(
                                String.format("User with the email %s was not found",username)
                        ));

            }
        };
    }

    @Override
    public UserResponseDTO getUser(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException(String.format("User with id %d not found", id)));
        return convertToDTO(user);
    }


    public UserResponseDTO convertToDTO(User user) {
        UserResponseDTO userDTO = new UserResponseDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setProfileImageUrl(generateProfileImageUrl(user.getProfileImage(), user));
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setLastUpdatedAt(user.getLastUpdatedAt());
        return userDTO;
    }

    private String generateProfileImageUrl(Byte[] profileImage, User user) {
        return baseUrl + user.getId();
    }
}
