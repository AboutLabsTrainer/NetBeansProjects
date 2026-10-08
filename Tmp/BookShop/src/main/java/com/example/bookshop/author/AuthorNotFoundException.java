package com.example.bookshop.author;

public class AuthorNotFoundException extends RuntimeException {

    private final Long authorId;

    public AuthorNotFoundException(Long authorId) {
        super("Autor mit ID " + authorId + " wurde nicht gefunden.");
        this.authorId = authorId;
    }

    public Long getAuthorId() {
        return authorId;
    }
}
