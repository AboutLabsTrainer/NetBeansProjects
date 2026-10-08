package de.ibb.userservice.controller;
import de.ibb.userservice.model.User;
import de.ibb.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**
 * REST-Schnittstelle der Benutzer.
 * Das Gateway leitet /api/users hierher, der Order-Service ruft einzelne Benutzer über Feign auf.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
