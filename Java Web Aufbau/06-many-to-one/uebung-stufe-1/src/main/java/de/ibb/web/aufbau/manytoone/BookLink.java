package de.ibb.web.aufbau.manytoone;

public final class BookLink {
    private BookLink() {}

    public static void attach(Book book, Author author) {
        // TODO: beide Seiten setzen. Dasselbe Buch nicht doppelt einhängen. null ablehnen.
        throw new UnsupportedOperationException("TODO");
    }
}
