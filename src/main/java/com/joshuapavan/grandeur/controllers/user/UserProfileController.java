package com.joshuapavan.grandeur.controllers.user;

import com.joshuapavan.grandeur.dto.userDto.UserResponseDTO;
import com.joshuapavan.grandeur.models.User;
import com.joshuapavan.grandeur.repositories.UserRepo;
import com.joshuapavan.grandeur.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserService userService;

    @GetMapping("/myprofile/{id}")
    public ResponseEntity<UserResponseDTO> getUserProfile(@RequestParam Long id){
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }
}
