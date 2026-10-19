package com.example.helloworldapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Bindet die Properties mit dem Präfix app typisiert an eine Bean.
 * Änderungen in application.properties oder in einem Profil landen hier, statt als lose Strings im Code zu stehen.
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
     * Gruppiert die Schalter unter app.features.
     * Spring bindet die verschachtelten Schlüssel an diese innere Klasse, weil sie Teil von AppProperties ist.
     */
    @Data
    public static class Features {
        private boolean emailEnabled;
        private boolean exportEnabled;
    }

    /**
     * Gruppiert die API-Zugangsdaten unter app.api.
     * Die Werte bleiben in der Konfiguration und werden über AppProperties injiziert.
     */
    @Data
    public static class Api {
        private String key;
        private String secret;
    }
}
