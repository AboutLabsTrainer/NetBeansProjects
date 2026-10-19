package de.ibb.boot.ak.security.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Läuft nach einer erfolgreichen Formular-Anmeldung, bevor weitergeleitet wird.
 * Die Filterkette ruft ihn aus der Form-Login-Konfiguration auf.
 */
@Component
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        String username = authentication.getName();
        String ip = request.getRemoteAddr();

        log.info("Successful login: User '{}' from IP {} at {}", username, ip, LocalDateTime.now());

        response.sendRedirect("/persons");
    }
}
