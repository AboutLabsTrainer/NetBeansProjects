package de.ibb.boot.basic.forms;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteController {
    @GetMapping("/")
    public String form(Model model) {
        model.addAttribute("note", new NoteForm());
        return "form";
    }

    @PostMapping("/notes")
    public String save(@Valid @ModelAttribute("note") NoteForm note, BindingResult result) {
        if (result.hasErrors()) {
            return "form";
        }
        return "saved";
    }
}
