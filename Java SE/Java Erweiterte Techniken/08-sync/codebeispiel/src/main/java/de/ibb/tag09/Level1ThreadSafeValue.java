package de.ibb.tag09;

/**
 * Einfache thread-sichere Getter/Setter-Klasse.
 */
public class Level1ThreadSafeValue {

    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized void setValue(int value) {
        this.value = value;
    }
}

