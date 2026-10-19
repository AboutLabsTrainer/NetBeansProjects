package de.ibb.boot.ak.security;

import de.ibb.boot.ak.security.entity.*;
import de.ibb.boot.ak.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Legt beim Start die Demo-Benutzer an, sobald der Container steht.
 * Die Passwörter gehen durch den UserService und werden dort kodiert, nicht im Klartext gespeichert.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) {
        log.info("Creating test users with authorities...");

        try {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin123");
            admin.setRole(Role.ADMIN);
            admin.setAuthorities(Set.of(
                    Authority.READ_PRIVILEGES,
                    Authority.WRITE_PRIVILEGES,
                    Authority.DELETE_PRIVILEGES,
                    Authority.ADMIN_PRIVILEGES
            ));
            userService.createUser(admin);
            log.info("Admin user created");
        } catch (Exception e) {
            log.warn("Admin user already exists: {}", e.getMessage());
        }

        try {
            User user = new User();
            user.setUsername("user");
            user.setPassword("user123");
            user.setRole(Role.USER);
            user.setAuthorities(Set.of(
                    Authority.READ_PRIVILEGES,
                    Authority.WRITE_PRIVILEGES
            ));
            userService.createUser(user);
            log.info("Normal user created");
        } catch (Exception e) {
            log.warn("Normal user already exists: {}", e.getMessage());
        }

        log.info("DataLoader finished.");
    }
}
