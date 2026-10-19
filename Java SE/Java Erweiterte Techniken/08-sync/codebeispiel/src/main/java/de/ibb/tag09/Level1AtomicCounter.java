package de.ibb.tag09;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Counter-Implementierung mit AtomicInteger.
 * Thread-safe ohne explizite Locks.
 */
public class Level1AtomicCounter {

    private final AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        counter.incrementAndGet();
    }

    public int getValue() {
        return counter.get();
    }
}

