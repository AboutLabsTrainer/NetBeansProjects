package com.example.bookshop.book;

public class BookNotFoundException extends RuntimeException {

    private final Long bookId;

    public BookNotFoundException(Long bookId) {
        super("Buch mit ID " + bookId + " wurde nicht gefunden.");
        this.bookId = bookId;
    }

    public Long getBookId() {
        return bookId;
    }
}
