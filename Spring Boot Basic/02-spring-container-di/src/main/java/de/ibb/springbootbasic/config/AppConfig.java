package de.ibb.springbootbasic.config;

import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

/**
 * AppConfig - Zentrale Konfigurationsklasse
 *
 * @Configuration - Sagt Spring: "Hier gibt's Bean-Definitionen!"
 *
 * Mit @Bean Methoden kannst du eigene Objekte als Beans registrieren.
 * Spring ruft diese Methoden einmal beim Start auf und verwaltet
 * die zurückgegebenen Objekte als Beans.
 *
 * Warum eigene @Bean Methoden?
 * - Objekte von Third-Party Libraries (z.B. Faker)
 * - Komplexe Initialisierung
 * - Objekte die du nicht mit @Component annotieren kannst
 */
@Configuration
public class AppConfig {

    /**
     * Faker Bean für realistische Testdaten
     *
     * @Bean - Spring ruft diese Methode beim Start auf
     * Der zurückgegebene Faker wird als Bean registriert
     * Andere Beans können ihn dann injiziert bekommen!
     *
     * Siehe: DatabasePersonService nutzt diesen Faker
     */
    @Bean
    public Faker faker() {
        System.out.println("🎲 Faker Bean wird erstellt (Locale: de)");
        return new Faker(new Locale("de"));
    }
}
