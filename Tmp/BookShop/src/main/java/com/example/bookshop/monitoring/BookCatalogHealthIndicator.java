package com.example.bookshop.monitoring;

import com.example.bookshop.book.BookRepository;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Component
public class BookCatalogHealthIndicator implements HealthIndicator {

    private final BookRepository bookRepository;

    public BookCatalogHealthIndicator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Health health() {
        try {
            long bookCount = bookRepository.count();

            return Health.up()
                    .withDetail("bookCount", bookCount)
                    .withDetail("message", "Der Buchkatalog ist erreichbar.")
                    .build();
        } catch (DataAccessException exception) {
            return Health.down(exception)
                    .withDetail("message", "Der Buchkatalog ist nicht erreichbar.")
                    .build();
        }
    }
}
