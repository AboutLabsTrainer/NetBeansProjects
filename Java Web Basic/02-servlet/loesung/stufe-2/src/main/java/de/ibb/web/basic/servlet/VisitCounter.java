package de.ibb.web.basic.servlet;

public final class VisitCounter {
    private int value;

    public synchronized int next() {
        value = value + 1;
        return value;
    }
}
