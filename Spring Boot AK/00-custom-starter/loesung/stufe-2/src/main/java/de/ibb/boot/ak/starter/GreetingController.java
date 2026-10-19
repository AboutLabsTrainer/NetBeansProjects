package de.ibb.boot.ak.starter;

import de.ibb.greeting.GreetingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/api/greet")
    public String greet(@RequestParam(defaultValue = "Ada") String name) {
        return greetingService.greet(name);
    }
}
