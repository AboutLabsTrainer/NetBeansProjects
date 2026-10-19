package de.ibb.flow;
public class Steuerung {
    public static String notenText(int note) {
        if (note == 1 || note == 2) {
            return "Sehr gut / Gut";
        }
        if (note == 3 || note == 4) {
            return "Ausreichend";
        }
        if (note == 5 || note == 6) {
            return "Nicht bestanden";
        }
        return "Ungültige Note";
    }
    public static String wochentag(int tag) {
        return switch (tag) {
            case 1 -> "Montag";
            case 2 -> "Dienstag";
            case 3 -> "Mittwoch";
            case 4 -> "Donnerstag";
            case 5 -> "Freitag";
            case 6 -> "Samstag";
            case 7 -> "Sonntag";
            default -> "Ungültiger Tag";
        };
    }
}
