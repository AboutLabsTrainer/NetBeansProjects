package de.ibb.scopes.service;

import de.ibb.scopes.model.Counter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Liefert bei jedem Abruf aus dem Container eine neue Instanz.
 * Eine einmal in einen Singleton injizierte Prototype-Bean wird nicht bei jedem Request neu erzeugt.
 */
@Service
@Scope("prototype")
public class PrototypeService {

    private final Counter counter = new Counter();

    public void increment() {
        counter.increment();
    }

    public int getValue() {
        return counter.getValue();
    }
}

