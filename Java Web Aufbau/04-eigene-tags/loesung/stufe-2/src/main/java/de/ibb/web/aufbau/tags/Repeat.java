package de.ibb.web.aufbau.tags;

public final class Repeat {
    private Repeat() {}

    public static String times(int count, String text) {
        if (count <= 0 || text == null) {
            return "";
        }
        return text.repeat(count);
    }
}
