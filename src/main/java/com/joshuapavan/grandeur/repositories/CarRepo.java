package com.joshuapavan.grandeur.repositories;

import com.joshuapavan.grandeur.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepo extends JpaRepository<Car, Long> {
}
