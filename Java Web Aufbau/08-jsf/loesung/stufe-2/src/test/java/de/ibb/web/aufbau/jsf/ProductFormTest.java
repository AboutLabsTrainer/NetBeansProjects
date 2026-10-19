package de.ibb.web.aufbau.jsf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

class ProductFormTest {
    @Test
    void addsStock() {
        assertTrue(ProductForm.errors("Stift", new BigDecimal("1"), 0).isEmpty());
        assertEquals(List.of("stock"), ProductForm.errors("Stift", new BigDecimal("1"), -1));
    }
}
