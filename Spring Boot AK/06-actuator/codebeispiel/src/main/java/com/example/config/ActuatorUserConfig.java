package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Legt den technischen Benutzer für die Actuator-Kette im Speicher an.
 * Es ist kein Datenbankkonto. Die zweite Filterkette der Anwendung benutzt ihn nicht.
 */
@Configuration
public class ActuatorUserConfig {

    @Bean
    public UserDetailsService actuatorUserDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails actuatorUser = User.builder()
                .username("actuator")
                .password(passwordEncoder.encode("actuator-secret-password"))
                .roles("ACTUATOR")
                .build();

        return new InMemoryUserDetailsManager(actuatorUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
