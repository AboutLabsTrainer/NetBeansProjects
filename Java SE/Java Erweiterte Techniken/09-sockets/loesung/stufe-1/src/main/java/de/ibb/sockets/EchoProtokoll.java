package de.ibb.sockets;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoProtokoll {

    private static final Pattern ID = Pattern.compile("\"id\"\\s*:\\s*(\\d+)");

    public static String antwort(String zeile) {
        return "Echo: " + zeile;
    }

    public static boolean beenden(String zeile) {
        return zeile != null && "bye".equalsIgnoreCase(zeile);
    }

    public static Optional<String> idAusJson(String json) {
        if (json == null) {
            return Optional.empty();
        }
        Matcher matcher = ID.matcher(json);
        if (matcher.find()) {
            return Optional.of(matcher.group(1));
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        System.out.println(antwort("Hallo"));
        System.out.println(beenden("BYE"));
        System.out.println(idAusJson("{\"id\": 42, \"title\": \"x\"}"));
    }
}
