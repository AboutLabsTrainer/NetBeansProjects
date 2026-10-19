package de.ibb.boot.ak.relations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    private final BookRepository books;

    public BookController(BookRepository books) {
        this.books = books;
    }

    @GetMapping("/api/books/all")
    public java.util.List<String> all() {
        return books.findAll().stream().map(Book::getTitle).toList();
    }

}
