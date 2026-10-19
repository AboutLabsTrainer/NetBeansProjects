package de.ibb.web.aufbau.manytoone;

public final class BookLink {
    private BookLink() {}

    public static void move(Book book, Author target) {
        if (book == null || target == null) {
            throw new IllegalArgumentException();
        }
        Author previous = book.getAuthor();
        if (previous != null) {
            previous.getBooks().remove(book);
        }
        book.setAuthor(target);
        if (!target.getBooks().contains(book)) {
            target.getBooks().add(book);
        }
    }
}
