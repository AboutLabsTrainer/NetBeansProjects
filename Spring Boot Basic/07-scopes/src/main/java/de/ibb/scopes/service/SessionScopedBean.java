package de.ibb.scopes.service;

import de.ibb.scopes.model.Counter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * Lebt so lange wie die HTTP-Session des Browsers.
 * Zwei Benutzer sehen getrennte Zähler, Wiederholungen im selben Browser denselben.
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionScopedBean {

    private final Counter counter = new Counter();

    public void increment() {
        counter.increment();
    }

    public int getValue() {
        return counter.getValue();
    }
}

