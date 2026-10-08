package de.ibb.greeting;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Auto-Configuration für den Greeting Spring Boot Starter.
 *
 * <p>Wird über {@code META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports}
 * registriert und von Spring Boot beim Start automatisch geladen, sobald der Starter
 * auf dem Classpath liegt. Es ist kein manuelles {@code @Import} nötig.</p>
 *
 * <p>{@link GreetingProperties} wird über {@code greeting.*} in {@code application.properties}
 * gebunden und steuert die Default-Implementierung.</p>
 */
@AutoConfiguration
// Nur aktiv, wenn die Greeting-API auf dem Classpath vorhanden ist
@ConditionalOnClass(GreetingService.class)
// Bindet greeting.* an GreetingProperties
@EnableConfigurationProperties(GreetingProperties.class)
public class GreetingAutoConfiguration {

    /**
     * Stellt den Standard-{@link GreetingService} bereit.
     *
     * <p>Bedingungen:</p>
     * <ul>
     *   <li>{@code @ConditionalOnMissingBean} – eine eigene {@link GreetingService}-Bean
     *       hat Vorrang und ersetzt diese Default-Implementierung</li>
     *   <li>{@code @ConditionalOnProperty(..., matchIfMissing = true)} – aktiv, wenn
     *       {@code greeting.enabled=true} oder die Property fehlt (Default: an)</li>
     * </ul>
     *
     * @param properties gebundene Konfiguration aus {@code greeting.*}
     * @return die Default-Implementierung
     */
    @Bean
    @ConditionalOnMissingBean(GreetingService.class)
    @ConditionalOnProperty(
            prefix = "greeting",
            name = "enabled",
            havingValue = "true",
            matchIfMissing = true
    )
    public GreetingService greetingService(GreetingProperties properties) {
        return new DefaultGreetingService(properties);
    }
}
