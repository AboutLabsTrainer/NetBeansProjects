package de.ibb.web.aufbau.manytoone;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BookLinkTest {
    @Test
    void movesTheBook() {
        Author first = new Author("Ada");
        Author second = new Author("Grace");
        Book book = new Book("Notizen");
        BookLink.move(book, first);
        BookLink.move(book, second);
        assertSame(second, book.getAuthor());
        assertFalse(first.getBooks().contains(book));
        assertTrue(second.getBooks().contains(book));
    }
}
