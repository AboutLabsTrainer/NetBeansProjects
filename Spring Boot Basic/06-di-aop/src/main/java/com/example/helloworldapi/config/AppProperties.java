package com.example.helloworldapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Typisierte Sicht auf die app-Properties.
 * Profil-Dateien können dieselben Schlüssel überschreiben, die Bean sieht danach den wirksamen Wert.
 */
@Component
@ConfigurationProperties(prefix = "app")
@Data
public class AppProperties {

    private String name;
    private String version;
    private int maxPersons;
    private Features features = new Features();
    private Api api = new Api();

    /**
     * Verschachtelte Properties unter app.features.
     * Sie werden zusammen mit AppProperties gebunden.
     */
    @Data
    public static class Features {
        private boolean emailEnabled;
        private boolean exportEnabled;
    }

    /**
     * Verschachtelte Properties unter app.api.
     * Sie werden zusammen mit AppProperties gebunden.
     */
    @Data
    public static class Api {
        private String key;
        private String secret;
    }
}
