package de.ibb.web.basic.http;

public final class MethodLabel {
    private MethodLabel() {}

    public static String of(String method) {
        if (method == null) {
            return "Unbekannt";
        }
        return switch (method.toUpperCase()) {
            case "GET" -> "Lesen";
            case "POST" -> "Senden";
            case "PUT" -> "Ersetzen";
            case "DELETE" -> "Löschen";
            default -> "Unbekannt";
        };
    }
}
