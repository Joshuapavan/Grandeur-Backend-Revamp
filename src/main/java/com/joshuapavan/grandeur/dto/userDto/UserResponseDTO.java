package com.joshuapavan.grandeur.dto.userDto;

import com.joshuapavan.grandeur.enums.Role;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDTO {

    private Long id;
    private String userName;
    private String email;
    private Role role;
    private String profileImageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;
}
