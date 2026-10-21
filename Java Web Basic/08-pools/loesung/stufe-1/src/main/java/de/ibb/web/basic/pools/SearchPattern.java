package de.ibb.web.basic.pools;

public final class SearchPattern {
    public static final String JNDI = "jdbc/ibb";

    private SearchPattern() {}

    public static String like(String term) {
        return "%" + term + "%";
    }
}
