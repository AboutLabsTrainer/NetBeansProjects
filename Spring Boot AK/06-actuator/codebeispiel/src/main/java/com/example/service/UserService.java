package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Legt Benutzer an und löst damit die Repository-Aufrufe aus, die Actuator sichtbar macht.
 */
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Counter userRegistrationCounter;
    private final Timer userRegistrationTimer;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       MeterRegistry meterRegistry) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

        this.userRegistrationCounter = Counter.builder("users.registered")
                .description("Total number of registered users")
                .tag("service", "user-service")
                .register(meterRegistry);

        this.userRegistrationTimer = Timer.builder("users.registration.duration")
                .description("Time taken to register a user")
                .tag("service", "user-service")
                .register(meterRegistry);
    }

    @Transactional
    public User createUser(User user) {
        try {
            return userRegistrationTimer.recordCallable(() -> {
                log.info("Creating user: {}", user.getUsername());

                if (!user.isValidUsername()) {
                    throw new IllegalArgumentException("Invalid username");
                }
                if (!user.isValidEmail()) {
                    throw new IllegalArgumentException("Invalid email");
                }
                if (userRepository.existsByUsername(user.getUsername())) {
                    throw new IllegalStateException("Username already exists");
                }
                if (userRepository.existsByEmail(user.getEmail())) {
                    throw new IllegalStateException("Email already exists");
                }

                user.setPassword(passwordEncoder.encode(user.getPassword()));
                User saved = userRepository.save(user);
                userRegistrationCounter.increment();
                log.info("User created: {}", saved.getId());
                return saved;
            });
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Failed to create user", e);
        }
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
