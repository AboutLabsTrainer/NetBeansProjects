package de.ibb.web.basic.pools;

public final class SearchPattern {
    public static final String JNDI = "jdbc/ibb";

    private SearchPattern() {}

    public static String like(String term) {
        // TODO: Suchbegriff in Prozentzeichen setzen, für LIKE ?.
        throw new UnsupportedOperationException("TODO");
    }
}
