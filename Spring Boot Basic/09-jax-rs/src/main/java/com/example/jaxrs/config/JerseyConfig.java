package com.example.jaxrs.config;

import com.example.jaxrs.exception.PersonNotFoundExceptionMapper;
import com.example.jaxrs.resource.PersonResource;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

/**
 * Registriert die JAX-RS-Ressourcen bei Jersey.
 * Der Application-Path ist das gemeinsame Präfix. Die Ressourcen bleiben Spring-Beans und können deshalb Services injiziert bekommen.
 */
@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(PersonResource.class);
        register(PersonNotFoundExceptionMapper.class);
    }
}
