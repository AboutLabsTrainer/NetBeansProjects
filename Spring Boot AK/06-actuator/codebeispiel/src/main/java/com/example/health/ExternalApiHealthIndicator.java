package com.example.health;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Health-Beitrag, der eine fremde URL über den RestTemplate-Bean abruft.
 * Actuator fasst solche Indikatoren im Health-Endpunkt zusammen.
 */
@Component
public class ExternalApiHealthIndicator implements HealthIndicator {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public ExternalApiHealthIndicator(RestTemplate restTemplate,
                                      @Value("${external.api.url}") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
    }

    @Override
    public Health health() {
        try {
            long start = System.currentTimeMillis();
            ResponseEntity<String> response = restTemplate.getForEntity(
                    apiUrl + "/ping",
                    String.class
            );
            long duration = System.currentTimeMillis() - start;

            if (response.getStatusCode().is2xxSuccessful()) {
                return Health.up()
                        .withDetail("api", "External Payment API")
                        .withDetail("responseTime", duration + "ms")
                        .withDetail("status", response.getStatusCode().value())
                        .build();
            }
            return Health.down()
                    .withDetail("api", "External Payment API")
                    .withDetail("status", response.getStatusCode().value())
                    .build();
        } catch (Exception e) {
            return Health.down()
                    .withDetail("api", "External Payment API")
                    .withDetail("error", "Connection failed: " + e.getMessage())
                    .withException(e)
                    .build();
        }
    }
}
