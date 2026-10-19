package de.ibb.logging;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Speichert die letzten Request-Log-Einträge für die Anzeige im Admin-Bereich.
 */
public final class RequestLogStore {

    private static final int MAX_ENTRIES = 100;
    private static final List<RequestLogEntry> ENTRIES = new CopyOnWriteArrayList<>();

    private RequestLogStore() {}

    public static void add(RequestLogEntry entry) {
        ENTRIES.add(0, entry);
        while (ENTRIES.size() > MAX_ENTRIES) {
            ENTRIES.remove(ENTRIES.size() - 1);
        }
    }

    public static List<RequestLogEntry> getRecentEntries() {
        return Collections.unmodifiableList(new ArrayList<>(ENTRIES));
    }
}
