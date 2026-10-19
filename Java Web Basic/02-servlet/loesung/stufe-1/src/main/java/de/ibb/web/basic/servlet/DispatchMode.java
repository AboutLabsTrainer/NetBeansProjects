package de.ibb.web.basic.servlet;

public final class DispatchMode {
    public enum Mode { FORWARD, INCLUDE }

    private DispatchMode() {}

    public static Mode from(String raw) {
        if (raw != null && raw.equalsIgnoreCase("forward")) {
            return Mode.FORWARD;
        }
        if (raw != null && raw.equalsIgnoreCase("include")) {
            return Mode.INCLUDE;
        }
        throw new IllegalArgumentException(String.valueOf(raw));
    }
}
