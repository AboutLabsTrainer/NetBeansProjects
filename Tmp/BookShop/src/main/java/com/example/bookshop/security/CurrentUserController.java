package com.example.bookshop.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/security")
public class CurrentUserController {

    @GetMapping("/me")
    public CurrentUserResponse currentUser(Authentication authentication) {
        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return new CurrentUserResponse(authentication.getName(), roles);
    }

    public record CurrentUserResponse(String username, List<String> authorities) {
    }
}
