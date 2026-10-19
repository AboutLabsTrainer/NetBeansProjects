package tech.eta.dataloader_d.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SimpleListController {
    // Simple in-memory list.   TODO: Upgrade to support database connections?
    private final List<String> items = new ArrayList<>();

    // 1. Display List
    @GetMapping("/simpleList")
    public String index(Model model) {
        model.addAttribute("items", items);
        return "simpleList";
    }

    // 2. Append String
    @PostMapping("/simpleList/add") // probably caught by ".anyRequest()" instead of properly
    public String addItem(@RequestParam String newItem) {
        if (newItem != null && !newItem.trim().isEmpty()) items.add(newItem);
        return "redirect:/simpleList";
    }

    // 3. Delete String by index
    @PostMapping("/simpleList/remove")
    public String removeItem(@RequestParam int index) {
        if (index >= 0 && index < items.size()) items.remove(index);
        return "redirect:/simpleList";
    }
}
