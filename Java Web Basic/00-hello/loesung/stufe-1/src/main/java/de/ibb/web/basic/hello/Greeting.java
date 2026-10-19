package de.ibb.web.basic.hello;

public final class Greeting {
    private Greeting() {}

    public static String text(String name) {
        if (name == null || name.isBlank()) {
            return "Hallo Welt";
        }
        return "Hallo, " + name.trim() + "!";
    }
}
