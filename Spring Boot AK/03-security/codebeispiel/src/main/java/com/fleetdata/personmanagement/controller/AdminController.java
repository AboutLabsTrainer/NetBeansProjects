package com.fleetdata.personmanagement.controller;

import com.fleetdata.personmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * MVC-Bereich, den die Filterkette nur für die Rolle ADMIN durchlässt.
 * Methodensicherheit kann denselben Zugriff zusätzlich auf einzelnen Methoden verlangen.
 */
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepository;

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin/users";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("userCount", userRepository.count());
        return "admin/dashboard";
    }
}
