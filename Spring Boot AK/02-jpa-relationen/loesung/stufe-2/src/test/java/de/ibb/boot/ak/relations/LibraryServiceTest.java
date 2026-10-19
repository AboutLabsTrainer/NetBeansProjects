package de.ibb.boot.ak.relations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(LibraryService.class)
class LibraryServiceTest {
    @Autowired
    private AuthorRepository authors;
    @Autowired
    private BookRepository books;
    @Autowired
    private LibraryService library;

    @Test
    void linksBookToAuthor() {
        Author author = new Author();
        author.setName("Ada");
        Book book = new Book();
        book.setTitle("Notizen");
        library.link(author, book);
        authors.saveAndFlush(author);
        Book stored = books.findAll().get(0);
        assertEquals("Notizen", stored.getTitle());
        assertEquals("Ada", stored.getAuthor().getName());

        assertEquals(1, books.findByAuthor_Name("Ada").size());

    }
}
