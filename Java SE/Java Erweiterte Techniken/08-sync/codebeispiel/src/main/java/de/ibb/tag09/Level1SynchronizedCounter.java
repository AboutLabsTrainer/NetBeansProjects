package de.ibb.tag09;

/**
 * Einfache Counter-Implementierung mit synchronized.
 * Demonstriert gegenseitigen Ausschluss auf Objektebene.
 */
public class Level1SynchronizedCounter {

    private int counter = 0;

    public synchronized void increment() {
        counter++;
    }

    public synchronized int getValue() {
        return counter;
    }
}

