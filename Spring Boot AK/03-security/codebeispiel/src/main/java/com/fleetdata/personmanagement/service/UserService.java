package com.fleetdata.personmanagement.service;

import com.fleetdata.personmanagement.entity.User;
import com.fleetdata.personmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Legt Benutzer an und lädt sie für CurrentUser nach.
 * Beim Anlegen wird das Passwort mit dem PasswordEncoder aus der Security-Konfiguration kodiert.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username bereits vergeben: " + user.getUsername());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setAccountNonLocked(true);
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User nicht gefunden: " + username));
    }
}
