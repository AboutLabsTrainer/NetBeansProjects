package com.example.bookshop.author;

public record AuthorResponse(
        Long id,
        String name
) {
    public static AuthorResponse from(AuthorEntity entity) {
        return new AuthorResponse(
                entity.getId(),
                entity.getName()
        );
    }
}
