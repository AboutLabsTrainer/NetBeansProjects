package de.ibb.web.basic.pools;

public final class SearchPattern {
    private SearchPattern() {}

    public static String like(String term) {
        if (term == null || term.isBlank()) {
            return "%";
        }
        return "%" + term.trim() + "%";
    }
}
