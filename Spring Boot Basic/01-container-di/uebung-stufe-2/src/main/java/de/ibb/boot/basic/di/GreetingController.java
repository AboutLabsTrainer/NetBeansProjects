package de.ibb.boot.basic.di;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private final GreetingService german;
    private final GreetingService english;

    public GreetingController(@Qualifier("germanGreeting") GreetingService german,
                              @Qualifier("englishGreeting") GreetingService english) {
        this.german = german;
        this.english = english;
    }

    @GetMapping("/api/greet")
    public String greet(@RequestParam(defaultValue = "Ada") String name) {
        return german.greet(name);
    }

    @GetMapping("/api/greet/en")
    public String greetEn(@RequestParam(defaultValue = "Ada") String name) {
        return english.greet(name);
    }

}
