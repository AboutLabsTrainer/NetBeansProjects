package de.ibb.sockets;

import java.util.Optional;

public class EchoProtokoll {

    public static String antwort(String zeile) {
        // TODO "Echo: " plus Zeile
        return "";
    }

    public static boolean beenden(String zeile) {
        // TODO bye, ohne trim
        return false;
    }

    public static Optional<String> idAusJson(String json) {
        // TODO erstes "id" : Zahl
        return Optional.empty();
    }

    public static void main(String[] args) {
        System.out.println(antwort("Hallo"));
        System.out.println(beenden("BYE"));
        System.out.println(idAusJson("{\"id\": 42, \"title\": \"x\"}"));
    }
}
