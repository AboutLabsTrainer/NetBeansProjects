package de.ibb.boot.ak.security.controller;

import de.ibb.boot.ak.security.security.CustomUserDetailsService;
import de.ibb.boot.ak.security.security.jwt.JwtTokenProvider;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Stellt Access- und Refresh-Token aus.
 * Login prüft Benutzername und Passwort über den AuthenticationManager. Die Token legt der JwtTokenProvider an.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApiController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final CustomUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = tokenProvider.generateToken(authentication);
        String refreshToken = tokenProvider.generateRefreshToken(authentication);

        return ResponseEntity.ok(new JwtResponse(accessToken, refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshRequest refreshRequest) {
        String refreshToken = refreshRequest.getRefreshToken();

        if (tokenProvider.validateRefreshToken(refreshToken)) {
            String username = tokenProvider.getUsernameFromToken(refreshToken);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());

            String newAccessToken = tokenProvider.generateToken(authentication);
            return ResponseEntity.ok(new JwtResponse(newAccessToken, refreshToken));
        }

        return ResponseEntity.status(401).body("Invalid refresh token");
    }

    /**
     * JSON-Körper für den Login mit Benutzername und Passwort.
     */
    @Data
    public static class LoginRequest {
        private String username;
        private String password;
    }

    /**
     * JSON-Körper, der nur das Refresh-Token enthält.
     */
    @Data
    public static class RefreshRequest {
        private String refreshToken;
    }

    /**
     * Antwort mit Access-Token, Refresh-Token und dem Schema Bearer.
     */
    @Data
    public static class JwtResponse {
        private final String accessToken;
        private final String refreshToken;
        private final String type = "Bearer";

        public JwtResponse(String accessToken, String refreshToken) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }
    }
}
