package de.ibb.setsmapschallenge;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU-Cache (Least Recently Used) für Level 3, Aufgabe 1.
 * Erweitert LinkedHashMap und entfernt automatisch den ältesten Eintrag,
 * wenn die maximale Kapazität überschritten wird.
 */
public class LruCache<K, V> extends LinkedHashMap<K, V> {
    private final int maxCapacity;

    public LruCache(int maxCapacity) {
        // accessOrder = true bedeutet: Zugriff auf Elemente ändert die Reihenfolge
        super(16, 0.75f, true);
        this.maxCapacity = maxCapacity;
    }

    /**
     * Überschreibt removeEldestEntry, um automatisch den ältesten Eintrag
     * zu entfernen, wenn die Kapazität überschritten wird.
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxCapacity;
    }
}
