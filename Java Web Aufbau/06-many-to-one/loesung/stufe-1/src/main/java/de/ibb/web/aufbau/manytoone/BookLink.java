package de.ibb.web.aufbau.manytoone;

public final class BookLink {
    private BookLink() {}

    public static void attach(Book book, Author author) {
        if (book == null || author == null) {
            throw new IllegalArgumentException();
        }
        book.setAuthor(author);
        if (!author.getBooks().contains(book)) {
            author.getBooks().add(book);
        }
    }
}
