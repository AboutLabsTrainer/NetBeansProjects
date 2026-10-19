package de.ibb.boot.ak.templates;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("heading", "Thymeleaf");
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        // TODO: model.addAttribute("about", "Eine Engine");
        return "about";
    }

}
