package com.joshuapavan.grandeur.repositories;

import com.joshuapavan.grandeur.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
