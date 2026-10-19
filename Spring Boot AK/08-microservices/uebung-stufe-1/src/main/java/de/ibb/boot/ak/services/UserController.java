package de.ibb.boot.ak.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/api/users")
    public java.util.List<String> users() {
        return java.util.List.of("Ada");
    }

}
