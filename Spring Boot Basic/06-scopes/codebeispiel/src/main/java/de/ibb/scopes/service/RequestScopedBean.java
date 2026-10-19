package de.ibb.scopes.service;

import de.ibb.scopes.model.Counter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * Lebt genau für einen HTTP-Request und wird danach verworfen.
 * Der Proxy im Singleton-Controller holt für jeden Request die passende Instanz.
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopedBean {

    private final Counter counter = new Counter();

    public void increment() {
        counter.increment();
    }

    public int getValue() {
        return counter.getValue();
    }
}

