package com.joshuapavan.grandeur;

import com.joshuapavan.grandeur.enums.Role;
import com.joshuapavan.grandeur.models.User;
import com.joshuapavan.grandeur.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
@RequiredArgsConstructor
public class GrandeurApplication implements CommandLineRunner {

	private final UserRepo userRepo;

	public static void main(String[] args) {
		SpringApplication.run(GrandeurApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Optional<User> admin = userRepo.findByRole(Role.ADMIN);
		if (admin.isEmpty()){
			User adminUser = new User();
			adminUser.setUserName("Admin");
			adminUser.setEmail("admin@grandeur.com");
			adminUser.setRole(Role.ADMIN);
			adminUser.setPassword(new BCryptPasswordEncoder().encode("Grandeur@123"));
			userRepo.save(adminUser);
		}
	}
}
