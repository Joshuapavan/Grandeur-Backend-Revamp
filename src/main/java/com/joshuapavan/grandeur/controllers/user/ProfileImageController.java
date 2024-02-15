package com.joshuapavan.grandeur.controllers.user;

import com.joshuapavan.grandeur.models.User;
import com.joshuapavan.grandeur.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users/profile-images")
@RequiredArgsConstructor
public class ProfileImageController {

    private final UserRepo userRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<byte[]> getUserProfileImage(@PathVariable Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent() && userOptional.get().getProfileImage() != null) {
            // Retrieve the profile image data from the user entity
            Byte[] profileImage = userOptional.get().getProfileImage();
            // Convert Byte[] to byte[]
            byte[] profileImageData = ArrayUtils.toPrimitive(profileImage);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Set content type to JPEG (change as needed)
            headers.setContentLength(profileImageData.length);
            return new ResponseEntity<>(profileImageData, headers, HttpStatus.OK);
        } else {
            // Profile image not found or user does not exist
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
