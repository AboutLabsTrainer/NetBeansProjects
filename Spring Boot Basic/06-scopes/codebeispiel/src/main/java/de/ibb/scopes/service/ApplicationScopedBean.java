package de.ibb.scopes.service;

import de.ibb.scopes.model.Counter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * Eine Instanz für das ganze ServletContext, also für alle Sessions dieser Webanwendung.
 * Der Scoped Proxy erlaubt es dem Singleton-Controller, die Bean trotzdem zu injizieren.
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ApplicationScopedBean {

    private final Counter counter = new Counter();

    public void increment() {
        counter.increment();
    }

    public int getValue() {
        return counter.getValue();
    }
}

