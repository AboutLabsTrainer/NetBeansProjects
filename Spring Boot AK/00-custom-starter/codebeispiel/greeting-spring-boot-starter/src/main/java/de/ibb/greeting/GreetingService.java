package de.ibb.greeting;

/**
 * Service für Begrüßungen.
 *
 * <p>Vertrag für Greeting-Implementierungen. Der Spring Boot Starter stellt
 * automatisch eine Default-Implementierung bereit, die über
 * {@code application.properties} konfigurierbar ist.</p>
 */
public interface GreetingService {

    /**
     * Erstellt eine Begrüßung für den angegebenen Namen.
     *
     * @param name der zu begrüßende Name, darf nicht {@code null} oder leer sein
     * @return die formatierte Begrüßung, niemals {@code null}
     * @throws IllegalArgumentException wenn {@code name} null oder leer ist
     */
    String greet(String name);
}

