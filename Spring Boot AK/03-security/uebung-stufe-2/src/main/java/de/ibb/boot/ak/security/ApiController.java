package de.ibb.boot.ak.security;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @GetMapping("/api/public")
    public String open() {
        return "offen";
    }

    @GetMapping("/api/me")
    public String me(Authentication authentication) {
        return authentication.getName();
    }

    @GetMapping("/api/admin")
    public String admin() {
        return "admin";
    }

}
