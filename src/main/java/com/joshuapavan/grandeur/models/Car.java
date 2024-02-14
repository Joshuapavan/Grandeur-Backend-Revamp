package com.joshuapavan.grandeur.models;

import com.joshuapavan.grandeur.enums.CarType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
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

    // Images
    @ElementCollection
    private List<byte[]> images;


    @CreatedDate
    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDate lastUpdatedAt;
}
