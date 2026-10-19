package de.ibb.boot.ak.relations;

import org.springframework.stereotype.Service;

@Service
public class LibraryService {
    public void link(Author author, Book book) {
        book.setAuthor(author);
        author.getBooks().add(book);
    }
}
