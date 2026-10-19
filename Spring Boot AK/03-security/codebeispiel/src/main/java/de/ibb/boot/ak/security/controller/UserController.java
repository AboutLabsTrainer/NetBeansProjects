package de.ibb.boot.ak.security.controller;

import de.ibb.boot.ak.security.entity.User;
import de.ibb.boot.ak.security.security.CurrentUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Zeigt den angemeldeten Benutzer und seine Authorities.
 * CurrentUser löst den Parameter zum Domain-User auf, statt nur den Namen aus dem SecurityContext zu lesen.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/me")
    public String currentUser(@AuthenticationPrincipal UserDetails user) {
        return "Eingeloggt als: " + user.getUsername();
    }

    @GetMapping("/me/authorities")
    public Collection<? extends GrantedAuthority> myAuthorities(Authentication authentication) {
        return authentication.getAuthorities();
    }

    @GetMapping("/me/full")
    public User currentUserFull(@CurrentUser User user) {
        return user;
    }
}
