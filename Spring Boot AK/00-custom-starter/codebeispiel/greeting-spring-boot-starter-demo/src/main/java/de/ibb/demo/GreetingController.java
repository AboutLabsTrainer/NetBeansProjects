package de.ibb.demo;

import de.ibb.greeting.GreetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST-Fassade über den injizierten {@link GreetingService}.
 *
 * <p>Der Service kommt aus der Auto-Configuration des Greeting-Starters.</p>
 */
@RestController
@RequestMapping("/greet")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    /**
     * Begrüßt den Namen aus dem Pfad.
     *
     * @param name Name in der URL, z.B. {@code /greet/Petra}
     * @return formatierte Begrüßung
     */
    @GetMapping("/{name}")
    public ResponseEntity<String> greet(@PathVariable("name") String name) {
        return ResponseEntity.ok(greetingService.greet(name));
    }

    /**
     * Begrüßt ohne Pfadparameter – verwendet den Default-Namen {@code "World"}.
     *
     * @return formatierte Begrüßung für {@code "World"}
     */
    @GetMapping
    public ResponseEntity<String> greetWorld() {
        return greet("World");
    }
}
