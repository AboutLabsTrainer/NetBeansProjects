package de.ibb.web.basic.servlet;

public final class DispatchMode {
    public enum Mode { FORWARD, INCLUDE }

    private DispatchMode() {}

    public static Mode from(String raw) {
        // TODO: "forward" und "include", ohne Großschreibung. Sonst IllegalArgumentException.
        throw new UnsupportedOperationException("TODO");
    }
}
