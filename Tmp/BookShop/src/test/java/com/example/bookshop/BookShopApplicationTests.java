package com.example.bookshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class BookShopApplicationTests {

    @Test
    void contextLoads() {
        // Smoke test: ensures the Spring context starts (H2 via test profile).
    }
}
