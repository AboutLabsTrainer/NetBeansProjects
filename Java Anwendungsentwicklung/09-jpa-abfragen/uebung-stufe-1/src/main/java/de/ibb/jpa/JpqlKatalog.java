package de.ibb.jpa;

/** JPQL-Texte aus JpqlDemo, ohne EntityManager. */
public final class JpqlKatalog {

    private JpqlKatalog() {
    }

    public static String aelterAls() {
        // TODO: SELECT p FROM Person p WHERE p.alter > :minAlter ORDER BY p.alter
        return "";
    }

    public static String anzahl() {
        // TODO: SELECT COUNT(p) FROM Person p
        return "";
    }
}
