package com.example.bookshop.monitoring;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class BookMetrics {

    private final Counter booksCreated;

    public BookMetrics(MeterRegistry registry) {
        this.booksCreated = Counter.builder("book.catalog.created")
                .description("Anzahl neu angelegter Bücher")
                .register(registry);
    }

    public void bookCreated() {
        booksCreated.increment();
    }
}
