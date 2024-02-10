package com.joshuapavan.grandeur.models;

import com.joshuapavan.grandeur.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Long id;

    @Column(unique = true, nullable = false, length = 15)
    private String userName;

    @Column(unique = true, nullable = false)
    @Email
    private String email;
    
    @Column(nullable = false, length = 30)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Car> cars;

    @Lob
    private Byte[] profileImage;

    @CreatedDate
    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDate lastUpdatedAt;
}
