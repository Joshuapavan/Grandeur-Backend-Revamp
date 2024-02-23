package com.joshuapavan.grandeur.models;

import com.joshuapavan.grandeur.enums.CarType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brandName;

    @Column(nullable = false, unique = true)
    private String carNumber;

    @Column(nullable = false)
    private String modelNumber;

    @Column(nullable = false)
    private String priceInLakhs;

    // Enums
    @Enumerated(EnumType.STRING)
    private CarType carType;

    // Associations
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private boolean isApproved = false;

    // Images
    @ElementCollection
    private List<byte[]> images;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedAt;
}
