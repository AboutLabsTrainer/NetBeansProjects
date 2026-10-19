package de.ibb.web.aufbau.tags;

public final class Truncate {
    private Truncate() {}

    public static String shorten(String text, int max) {
        if (text == null) {
            return "";
        }
        if (text.length() <= max) {
            return text;
        }
        if (max <= 1) {
            return "…";
        }
        return text.substring(0, max - 1) + "…";
    }
}
