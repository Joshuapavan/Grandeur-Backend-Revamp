package com.joshuapavan.grandeur.repositories;

import com.joshuapavan.grandeur.enums.Role;
import com.joshuapavan.grandeur.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByRole(Role role);
}
