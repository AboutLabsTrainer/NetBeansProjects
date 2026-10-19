package de.ibb.web.basic.hello;

public final class DayGreeting {
    private DayGreeting() {}

    public static String text(String name, int hour) {
        String who = (name == null || name.isBlank()) ? "Gast" : name.trim();
        String part;
        if (hour >= 5 && hour <= 11) {
            part = "Guten Morgen";
        } else if (hour >= 12 && hour <= 17) {
            part = "Guten Tag";
        } else if (hour >= 18 && hour <= 22) {
            part = "Guten Abend";
        } else {
            part = "Gute Nacht";
        }
        return part + ", " + who + "!";
    }
}
