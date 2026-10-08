package com.example.bookshop.book;

import com.example.bookshop.author.AuthorResponse;

public record BookResponse(
        Long id,
        String title,
        AuthorResponse author,
        String isbn
) {
    public static BookResponse from(BookEntity entity) {
        return new BookResponse(
                entity.getId(),
                entity.getTitle(),
                AuthorResponse.from(entity.getAuthor()),
                entity.getIsbn()
        );
    }
}
