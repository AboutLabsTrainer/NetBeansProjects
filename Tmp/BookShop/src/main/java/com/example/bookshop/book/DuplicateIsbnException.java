package com.example.bookshop.book;

public class DuplicateIsbnException extends RuntimeException {

    private final String isbn;

    public DuplicateIsbnException(String isbn) {
        super("ISBN " + isbn + " ist bereits vergeben.");
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }
}
