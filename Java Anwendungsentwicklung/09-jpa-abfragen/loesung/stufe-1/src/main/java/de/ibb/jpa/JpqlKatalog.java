package de.ibb.jpa;

public final class JpqlKatalog {

    private JpqlKatalog() {
    }

    public static String aelterAls() {
        return "SELECT p FROM Person p WHERE p.alter > :minAlter ORDER BY p.alter";
    }

    public static String anzahl() {
        return "SELECT COUNT(p) FROM Person p";
    }
}
