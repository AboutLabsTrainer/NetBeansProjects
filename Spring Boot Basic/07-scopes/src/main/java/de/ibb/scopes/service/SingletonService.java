package de.ibb.scopes.service;

import de.ibb.scopes.model.Counter;
import org.springframework.stereotype.Service;

/**
 * Der Default-Scope: eine Instanz für die gesamte Anwendung.
 * Jeder Aufruf über den Controller trifft denselben Zähler.
 */
@Service
public class SingletonService {

    private final Counter counter = new Counter();

    public void increment() {
        counter.increment();
    }

    public int getValue() {
        return counter.getValue();
    }
}

