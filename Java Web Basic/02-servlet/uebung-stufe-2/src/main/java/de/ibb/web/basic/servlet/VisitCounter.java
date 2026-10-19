package de.ibb.web.basic.servlet;

public final class VisitCounter {
    private int value;

    public synchronized int next() {
        // TODO: bei jedem Aufruf um eins erhöhen und den neuen Stand liefern. Start ist 0.
        throw new UnsupportedOperationException("TODO");
    }
}
