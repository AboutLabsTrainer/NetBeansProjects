package com.example.personmanagement.jaxrs;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import jakarta.ws.rs.ApplicationPath;

/**
 * Hängt die JAX-RS-Ressource unter dem Präfix /jaxrs ein.
 * Jersey und Spring MVC bedienen dadurch getrennte Pfade in derselben Anwendung.
 */
@Configuration
@ApplicationPath("/jaxrs")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(PersonResource.class);
    }
}

