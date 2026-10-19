package de.ibb.boot.ak.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PriceServiceTest {
    @Autowired
    private PriceService service;

    @Test
    void secondCallUsesCache() {
        int before = service.priceCalls();
        int first = service.price("A");
        assertEquals(first, service.price("A"));
        assertEquals(before + 1, service.priceCalls());

    }
}
