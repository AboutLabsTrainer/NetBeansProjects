package de.ibb.scopes.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Zähler, den die Scope-Beans hochzählen.
 * Die Klasse ist selbst keine Spring-Bean. Ihre Lebensdauer ist die der Bean, die sie besitzt.
 */
public class Counter {

    private final AtomicInteger value = new AtomicInteger(0);

    public void increment() {
        value.incrementAndGet();
    }

    public int getValue() {
        return value.get();
    }
}

