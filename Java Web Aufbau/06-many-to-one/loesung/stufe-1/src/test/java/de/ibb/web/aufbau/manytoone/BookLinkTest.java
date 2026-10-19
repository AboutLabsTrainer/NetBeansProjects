package de.ibb.web.aufbau.manytoone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class BookLinkTest {
    @Test
    void wiresBothSidesOnce() {
        Author author = new Author("Ada");
        Book book = new Book("Notizen");
        BookLink.attach(book, author);
        BookLink.attach(book, author);
        assertSame(author, book.getAuthor());
        assertEquals(1, author.getBooks().size());
        assertThrows(IllegalArgumentException.class, () -> BookLink.attach(book, null));
    }
}
